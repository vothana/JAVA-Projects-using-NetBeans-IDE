/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Categories;

import MessageBox.Message;
import MessageBox.MessageView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nhean Serevoutey
 */
public class CategoriesController implements Initializable {
    private Fucntions fucntions = new Fucntions();
    
    @FXML
    private TextField txtname;
    @FXML
    private Button btnSubmit;
    @FXML
    private TableView<Categories> tableCat;
    @FXML
    private TableColumn<Categories, Integer> catID;
    @FXML
    private TableColumn<Categories, String> catName;
    @FXML
    private Label labelID;
    @FXML
    private Button exit;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSubmit1;
    @FXML
    private StackPane rooter;
    @FXML
    private AnchorPane anchorpane;
    
    MessageView messageView = new MessageView();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showTable();
        addListenerForTable();
    }    

    @FXML
    private void ActionOnCategory(ActionEvent event) throws IOException {
        String check = btnSubmit.getText();
        
        if ( "Update".equals(check)){
            getConnection();
            try {
                String name = txtname.getText();
                Categories categories = tableCat.getSelectionModel().getSelectedItem();
                String query = "UPDATE `Categories` SET CategoryName = '" + name
                                + "' WHERE CategoryID = '" + categories.getId() + "'";
                executeQueryUpdate(query);
                showTable();
                close();
                messageView.messageBox(btnSubmit1, "Success updated category : " + name, "Success");
            } catch (Exception e) {
                System.err.print(e.getMessage());
                close();
            } 
        }else if( "Submit".equals(check)){
            insertRecord();
        }
    }
    
    
    
    public void showTable(){
        ObservableList<Categories> list = getTableList();
        catID.setCellValueFactory(new PropertyValueFactory<Categories, Integer>("id"));
        catName.setCellValueFactory(new PropertyValueFactory<Categories, String>("name"));
        
        tableCat.setItems(list);
    }
    
        //loop all the data from database
    private ObservableList<Categories> getTableList() {
        ObservableList<Categories> tableList = FXCollections.observableArrayList();
        
        String query = "SELECT * FROM Categories";
        ResultSet rs = query(query);
        
        Categories tables ;
        try {
            while(rs.next()){
                tables = new Categories(rs.getInt("CategoryID") , 
                                       rs.getString("CategoryName"));
                tableList.add(tables);
            }
        } catch (SQLException e) {
            Fucntions obj = new Fucntions();
            obj.printSQLException(e);
        }
        
        return tableList;
    }
    
    //Insert a record
    private void insertRecord() throws IOException{
           int id             = Integer.parseInt(labelID.getText().replaceAll("[^0-9]", ""));
           String name        = txtname.getText();
  
           String query       = "INSERT INTO `Categories` Values(" + id 
                                + "  , '" + name + "')" ;
            if (!name.isEmpty()){
                executeQueryUpdate(query);
                showTable();
                clearForm();
                close();
                messageView.messageBox(btnSubmit1, "Success creating new category : " + name, "Success");
            }else{ 
                messageView.messageBox(btnSubmit1, "Please insert valid name !", "Wrong");
            } 
    }
    
    public void idIncrement(){
        String query = "SELECT MAX(CategoryID) FROM Categories";
        String cid   = String.valueOf(getMaxId(query) + 1);
        labelID.setText("ID :" + cid);
    }
    //Clear form
    private void clearForm(){
        txtname.setText("");
        idIncrement();
    }
        //when click a row of table will bring data to text feilds
    private void addListenerForTable(){
       tableCat.getSelectionModel().selectedItemProperty().addListener((obs , oldSelection , newSelection) -> {
           if(newSelection != null){
               btnDelete.setDisable(false);
               btnSubmit.setText("Update");
               btnSubmit.setDisable(false);
               
               labelID.setText(String.valueOf("ID : " + newSelection.getId()));
               txtname.setText(newSelection.getName());
               
           }else{
               clearForm();
               btnDelete.setDisable(true);
           }
        });
    }   
    
    
    @FXML
    private void exit(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void deleteCategory(ActionEvent event) throws IOException, InterruptedException {
        getConnection();
        MessageBox.Message message = new Message();
        Categories categories = tableCat.getSelectionModel().getSelectedItem();
        
        String catName = categories.getName();
        String catNameFromTextField = txtname.getText();
        
        String messagetext = "Are you sure ? Delete Category's name : " + catNameFromTextField;
        int ok = message.popupMessage(anchorpane, messagetext, "Confirm");
        
        if (ok > 0){
            try {
                if(catNameFromTextField.equals(catName)){
                    String q = "SELECT * FROM products p INNER JOIN Categories c "
                            + " ON p.CategoryID = c.CategoryID WHERE c.CategoryID ='" + categories.getId() + "'";
                    ///trying to check if category has product inside or not //you can not delete if there have
                    try {
                        ResultSet rs = query(q);
                        rs.next();
                        if(!rs.getString("CategoryID").isEmpty()){ 
                            messageView.messageBox(btnSubmit1,catName +  " Present products !", "Wrong");
                        }
                    } catch (Exception e) {
                        String query = "DELETE FROM `categories` WHERE categoryID = '" + categories.getId() + "'";
                        executeQueryUpdate(query);
                        showTable();
                        close();
                        btnSubmit.setText("Submit");
                        messageView.messageBox(btnSubmit1, "Success deleting Category's name : " + catName, "Success");
                    }
                }else{
                    messageView.messageBox(btnSubmit1, "Opp! No " + catNameFromTextField + " in categories", "Confirm");
                }
            } catch (Exception e) {
                System.err.print(e.getMessage());
                messageView.messageBox(btnSubmit1, "Opp! Something wrong ! Please restart application", "Confirm");
                close();
            }
        } 
    }   
    @FXML
    private void newCategory(ActionEvent event) {
        clearForm();
        btnDelete.setDisable(true);
        btnSubmit.setText("Submit");
        btnSubmit.setDisable(false);
    }

}
