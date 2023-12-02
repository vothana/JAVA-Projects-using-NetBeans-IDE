/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static possystem.DBConfig.*;
import possystem.Fucntions;
import possystem.Slider;

/**
 * FXML Controller class
 *
 * @author Reachana Yann
 */
public class CustomersController implements Initializable {

    @FXML
    private TableView<Customers> tableView;
    @FXML
    private TableColumn<Customers, Integer> cusID;
    @FXML
    private TableColumn<Customers, String> cusName;
    @FXML
    private TableColumn<Customers, String> cusGender;
    @FXML
    private TableColumn<Customers, String> cusPhoneNumber;
    @FXML
    private TableColumn<Customers, String> cusEmail;
    @FXML
    private TableColumn<Customers, String> cusAddress;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private ComboBox<String> txtGender;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextArea txtAddress;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClose;
    @FXML
    private AnchorPane anchorpane;
    private String preventClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtGender.setItems(FXCollections.observableArrayList("Male", "Female"));
        idIncrement();
        txtId.setEditable(false);
        txtId.setDisable(true);
        // txtAddress.setWrapText(true);
        addListenerForTable();
        showTable();
    }

    public void idIncrement() {
        String query = "SELECT MAX(CustomerID) FROM customers";
        String cid = String.valueOf(getMaxId(query) + 1);
        txtId.setText(cid);
    }

    // show the table
    public void showTable() {
        ObservableList<Customers> list = getTableList();
        cusID.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("id"));
        cusName.setCellValueFactory(new PropertyValueFactory<Customers, String>("name"));
        cusGender.setCellValueFactory(new PropertyValueFactory<Customers, String>("gender"));
        cusPhoneNumber.setCellValueFactory(new PropertyValueFactory<Customers, String>("phoneNumber"));
        cusEmail.setCellValueFactory(new PropertyValueFactory<Customers, String>("email"));
        cusAddress.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));

        tableView.setItems(list);
    }

    // loop all the data from database
    private ObservableList<Customers> getTableList() {
        ObservableList<Customers> tableList = FXCollections.observableArrayList();

        String query = "SELECT * FROM customers";
        ResultSet rs = query(query);

        Customers tables;
        try {
            while (rs.next()) {
                tables = new Customers(rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getString("Gender"),
                        rs.getString("Phonenumber"),
                        rs.getString("Email"),
                        rs.getString("Address"));
                tableList.add(tables);
            }
        } catch (SQLException e) {
            Fucntions obj = new Fucntions();
            obj.printSQLException(e);
        }

        return tableList;
    }

    // Insert a record
    private void insertRecord() {
        int id = Integer.valueOf(txtId.getText());
        String name = txtName.getText();
        String gender = txtGender.getValue();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String query = "INSERT INTO `customers` Values(" + id
                + "  , '" + name
                + "' , '" + gender
                + "' , '" + phoneNumber
                + "' , '" + email
                + "' , '" + address + "')";
        if (!name.isEmpty()) {
            executeQueryUpdate(query);
            showTable();
            clearForm();
        }
    }

    // Clear form
    private void clearForm() {
        idIncrement();
        txtName.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
    }

    // when click a row of table will bring data to text feilds
    private void addListenerForTable() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);

                txtId.setText(String.valueOf(newSelection.getId()));
                txtName.setText(newSelection.getName());
                txtGender.setValue(newSelection.getGender());
                txtPhoneNumber.setText(newSelection.getPhoneNumber());
                txtEmail.setText(newSelection.getEmail());
                txtAddress.setText(newSelection.getAddress());
            } else {
                clearForm();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        });
    }

    @FXML
    private void newCustomer(ActionEvent event) {
        clearForm();
    }

    @FXML
    private void saveCustomer(ActionEvent event) {
        insertRecord();
    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
        getConnection();
        try {
            Customers customer = tableView.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM `Customers` WHERE CustomerID = '" + customer.getId() + "'";
            executeQueryUpdate(query);
            showTable();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

    @FXML
    private void updateCustomers(ActionEvent event) {
        getConnection();
        try {
            Customers customer = tableView.getSelectionModel().getSelectedItem();
            String query = "UPDATE `Customers` SET CustomerName = '" + txtName.getText()
                    + "' , Gender         = '" + txtGender.getValue()
                    + "' , Phonenumber    = '" + txtPhoneNumber.getText()
                    + "' , Email          = '" + txtEmail.getText()
                    + "' , Address        = '" + txtAddress.getText()
                    + "' WHERE CustomerID = '" + customer.getId() + "'";
            executeQueryUpdate(query);
            showTable();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

    @FXML
    private void closeForm(ActionEvent event) throws IOException {
        if("clickedFromFormOrder".equals(preventClose)){
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }else{
            Slider silder = new Slider();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/possystem/Dashboard.fxml"));
            Parent root = loader.load();
            silder.slideTopToBottom(loader, root, anchorpane, btnClose, true);
        }
    }

    public void preventClose(String preventClose){
        this.preventClose = preventClose;
    }
    
}
