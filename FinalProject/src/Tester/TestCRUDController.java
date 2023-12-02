/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Tester;

import possystem.Fucntions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField; 
import java.sql.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class TestCRUDController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdae;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtName;
    @FXML
    private TableView<TestCRUD> tableView;
    @FXML
    private TableColumn<TestCRUD, Integer> colid;
    @FXML
    private TableColumn<TestCRUD, String> colName;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOs
        showTable();
    }    
    
    public void showTable(){
        ObservableList<TestCRUD> list = getTableList();
        colid.setCellValueFactory(new PropertyValueFactory<TestCRUD, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<TestCRUD, String>("orderName"));
        tableView.setItems(list);
    }

    private ObservableList<TestCRUD> getTableList() {
        ObservableList<TestCRUD> tableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM crud";
        ResultSet rs = possystem.DBConfig.query(query);
        
        TestCRUD tables;
        try {
            while(rs.next()){
            tables = new TestCRUD(rs.getInt("id"), rs.getString("OrderName"));
            tableList.add(tables);
        }
        } catch (SQLException e) {
            Fucntions obj = new Fucntions();
            obj.printSQLException(e);
        }
        
        return tableList;
    }
    
}
