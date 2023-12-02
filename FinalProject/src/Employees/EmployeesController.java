/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Employees;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import possystem.Fucntions;
import static possystem.DBConfig.*;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import possystem.Slider;
/**
 * FXML Controller class
 *
 * @author Sarath
 */
public class EmployeesController implements Initializable {

    @FXML
    private TextField txtid;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtsalary;
    @FXML
    private ComboBox<String> txtxgender;
    @FXML
    private TextField txtphonenumber;
    @FXML
    private TextField txthiredate;
    @FXML
    private TextField txtaddress;
    @FXML
    private TextField txtDOB;
    @FXML
    private TextField txtemail;
    @FXML
    private Button btnnew;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnsave;
    @FXML
    private Button btnclose;
    @FXML
    private TableView<Employees> tableview;
    @FXML
    private TableColumn<Employees, Integer> Empid;
    @FXML
    private TableColumn<Employees, String> Empname;
    @FXML
    private TableColumn<Employees, String> empgender;
    @FXML
    private TableColumn<Employees, String> EmpDOB;
    @FXML
    private TableColumn<Employees, String> Empphonenumber;
    @FXML
    private TableColumn<Employees, String> Empemail;
    @FXML
    private TableColumn<Employees, String> Empsalary;
    @FXML
    private TableColumn<Employees, String> Empaddress;
    @FXML
    private TableColumn<Employees, String> Emphiredate;

    private Fucntions fucntions = new Fucntions();
    @FXML
    private BorderPane rooter;
    @FXML
    private AnchorPane anchorpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        showTable();
        addListenerForTable();
    }    
    
     public void showTable() {
        ObservableList<Employees> list = getTableList();
        Empid.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("id"));
        Empname.setCellValueFactory(new PropertyValueFactory<Employees, String>("name"));
        empgender.setCellValueFactory(new PropertyValueFactory<Employees, String>("gender"));
        EmpDOB.setCellValueFactory(new PropertyValueFactory<Employees, String>("DOB"));
        Empphonenumber.setCellValueFactory(new PropertyValueFactory<Employees, String>("phoneNumber"));
        Empemail.setCellValueFactory(new PropertyValueFactory<Employees, String>("email"));
        Empsalary.setCellValueFactory(new PropertyValueFactory<Employees, String>("salary"));
        Empaddress.setCellValueFactory(new PropertyValueFactory<Employees, String>("address"));
        Emphiredate.setCellValueFactory(new PropertyValueFactory<Employees, String>("hiredate"));
        
        tableview.setItems(list);
    }
    
     private ObservableList<Employees> getTableList() {
        ObservableList<Employees> tableList = FXCollections.observableArrayList();

        String query = "SELECT * FROM Employees";
        ResultSet rs = query(query);

        Employees tables;
        try {
            while (rs.next()) {
                tables = new Employees(rs.getInt("EmployeeID"),
                        rs.getString("EmployeeName"),
                        rs.getString("Gender"),
                        rs.getString("DOB"),
                        rs.getString("Phonenumber"),
                        rs.getString("Email"),
                        rs.getString("Salary"),
                        rs.getString("Address"),
                        rs.getString("HireDate"));
                tableList.add(tables);
            }
        } catch (SQLException ex) {
     
            fucntions.printSQLException((SQLException) ex);
        }

        return tableList;
    }
     
// First ClearEmployees     
     public void idIncrement() {
        String query = "SELECT MAX(EmployeeID) FROM Employees";
        String cid = String.valueOf(getMaxId(query) + 1);
        txtid.setText(cid);
     }
    private void clearForm() {
        idIncrement();
        txtname.setText("");
        txtxgender.setValue("");
        txtDOB.setText("");
        txtphonenumber.setText("");
        txtemail.setText("");
        txtsalary.setText("");
        txtaddress.setText("");
        txthiredate.setText("");
    } 

    @FXML
    private void newEmployees(ActionEvent event) {
         clearForm();
    }
// END ClearEmployees
    
   
    
// First saveEmployees    
    private void insertRecord() {
        int id = Integer.valueOf(txtid.getText());
        String name = txtname.getText();
        String gender = txtxgender.getValue();
        String DOB = txtDOB.getText();
        String phoneNumber = txtphonenumber.getText();
        String email = txtemail.getText();
        String salary = txtsalary.getText();
        String address = txtaddress.getText();
        String hiredate = txthiredate.getText();
        String query = "INSERT INTO `Employees` Values(" + id
                + "  , '" + name
                + "' , '" + gender
                + "' , '" + DOB
                + "' , '" + phoneNumber
                + "' , '" + email
                + "' , '" + salary
                + "' , '" + address
                + "' , '" + hiredate + "')";
        if (!name.isEmpty()) {
            executeQueryUpdate(query);
            showTable();
            clearForm();
        }
    }
    @FXML
    private void saveEmployees(ActionEvent event) {
        insertRecord();
    }
// End Save Employees

// First UpdateEmployees    
     @FXML
    private void updateEmployees(ActionEvent event){
        getConnection();
        try {
            Employees employees = tableview.getSelectionModel().getSelectedItem();
            String query = "UPDATE `Employees` SET EmployeeName = '" + txtname.getText()
                    + "' , Gender         = '" + txtxgender.getValue()
                    + "' , DOB            = '" + txtDOB.getText()
                    + "' , PhoneNumber    = '" + txtphonenumber.getText()
                    + "' , Email          = '" + txtemail.getText()
                    + "' , Salary         = '" + txtsalary.getText()
                    + "' , Address        = '" + txtaddress.getText()
                    + "' , HireDate       = '" + txthiredate.getText()
                    + "' WHERE EmployeeID = '" + employees.getId() + "'";
            executeQueryUpdate(query);
            showTable();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

// End UpdateEmployees 
    
// First Delete Employees    
    @FXML
    private void deleteEmployees(ActionEvent event) {
          getConnection();
        try {
            Employees Employees = tableview.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM `Employees` WHERE EmployeeID = '" + Employees.getId() + "'";
            executeQueryUpdate(query);
            showTable();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

 // End Delete Employees
    
// First CloseForm    
    @FXML
    private void closeform(ActionEvent event) throws IOException {
        Slider silder = new Slider();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/possystem/Dashboard.fxml"));
        Parent root = loader.load();
        silder.slideRightToLeft(loader, root , anchorpane , btnsave, true);
    }
// End CloseForm    
    
// First Showtable    
    private void addListenerForTable() {
        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnupdate.setDisable(false);
                btndelete.setDisable(false);

                txtid.setText(String.valueOf(newSelection.getId()));
                txtname.setText(newSelection.getName());
                txtxgender.setValue(newSelection.getGender());
                txtDOB.setText(newSelection.getDOB());
                txtphonenumber.setText(newSelection.getPhoneNumber());
                txtemail.setText(newSelection.getEmail());
                txtsalary.setText(newSelection.getSalary());
                txtaddress.setText(newSelection.getAddress());
                txthiredate.setText(newSelection.getHiredate());
            } else {
                clearForm();
                btnupdate.setDisable(true);
                btndelete.setDisable(true);
            }
        });
    }

// End ShowTable
}
