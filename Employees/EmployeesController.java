/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Employees;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import possystem.Fucntions;
import possystem.DBConfig;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class EmployeesController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private Button btnsave;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> empID;
    @FXML
    private TableColumn<?, ?> empName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveEmployee(ActionEvent event) {
    }
    
}
