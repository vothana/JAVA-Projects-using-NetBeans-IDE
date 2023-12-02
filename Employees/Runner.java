/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employees;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;
import possystem.Fucntions;


/**
 *
 * @author Sarath
 */
public class Runner extends Application{
    private  Fucntions fucntion = new Fucntions();
    @Override
    public void start(Stage stage) throws Exception {
        try {
            fucntion.openModelWindow("/Employees/Employees.fxml", "Employees Management");
    
            
        } catch (Exception e) {
            
           System.err.print(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
