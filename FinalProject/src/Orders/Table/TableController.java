/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Orders.Table;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class TableController implements Initializable {

    @FXML
    private Button table1;
    @FXML
    private Button table2;
    @FXML
    private Button table4;
    @FXML
    private Button table5;
    @FXML
    private Button table3;
    @FXML
    private Button table6;
    @FXML
    private Button table7;
    @FXML
    private Button table8;
    @FXML
    private Button table9;
    @FXML
    private Button table10;
    @FXML
    private Button table11;
    @FXML
    private Button table12;
    @FXML
    private Button table13;
    @FXML
    private Button table14;
    @FXML
    private Button table15;
    @FXML
    private Button btnDone;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clicked();
    }
    
    int clicked;
    private int clicked(){
        table1.setOnAction(e1 -> { clicked = 1 ;  returnTableNum(clicked);   exit(); });
        table2.setOnAction(e1 -> { clicked = 2 ;  returnTableNum(clicked);   exit(); });
        table3.setOnAction(e1 -> { clicked = 3 ;  returnTableNum(clicked);   exit(); });
        table4.setOnAction(e1 -> { clicked = 4 ;  returnTableNum(clicked);   exit(); });
        table5.setOnAction(e1 -> { clicked = 5 ;  returnTableNum(clicked);   exit(); });
        table6.setOnAction(e1 -> { clicked = 6 ;  returnTableNum(clicked);   exit(); });
        table7.setOnAction(e1 -> { clicked = 7 ;  returnTableNum(clicked);   exit(); });
        table8.setOnAction(e1 -> { clicked = 8 ;  returnTableNum(clicked);   exit(); });
        table9.setOnAction(e1 -> { clicked = 9 ;  returnTableNum(clicked);   exit(); });
        table10.setOnAction(e1 -> { clicked = 10 ;  returnTableNum(clicked); exit(); });
        table11.setOnAction(e1 -> { clicked = 11 ;  returnTableNum(clicked); exit(); });
        table12.setOnAction(e1 -> { clicked = 12 ;  returnTableNum(clicked); exit(); });
        table13.setOnAction(e1 -> { clicked = 13 ;  returnTableNum(clicked); exit(); });
        table14.setOnAction(e1 -> { clicked = 14 ;  returnTableNum(clicked); exit(); });
        table15.setOnAction(e1 -> { clicked = 15 ;  returnTableNum(clicked); exit(); });
        
        return clicked;
    }
    
    private void returnTableNum(int clicked){
        System.out.println(clicked);
    }
    
    public int getTableNumber(){
        return clicked;
    }

    @FXML
    private void exit(ActionEvent event) {
        exit();
    }
    
    void exit(){
        final Stage stage = (Stage) anchor.getScene().getWindow();
        stage.close();
    }
    
    
}
