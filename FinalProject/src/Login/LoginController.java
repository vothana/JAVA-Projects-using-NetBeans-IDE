/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Login;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static possystem.DBConfig.getConnection;
import possystem.Fucntions;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import possystem.DBConfig;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class LoginController implements Initializable {
    private static Fucntions fucntion = new Fucntions();
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtusername.setFocusTraversable(true);
    }
    
    private static Connection con;
    final String DB_URL = "jdbc:mysql://localhost:3306/possystem";
    final String DB_USER = "root";
    final String DB_PASS = ""; 
    //Connect to MYSQL database

    private boolean validate(String username , String password) throws ClassNotFoundException{
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);  
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println (preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }catch (SQLException ex) {
           fucntion.printSQLException(ex);
           System.err.print(ex.getMessage());
        }
        return false;
    }
    
    
    private void login(ActionEvent event) throws ClassNotFoundException{
        Window owner = txtusername.getScene().getWindow();
   
        if (txtusername.getText().isEmpty()){
            fucntion.showAlert(Alert.AlertType.ERROR, owner, "Please provide valid username", "Form Error");
            return;
        } 
        
        if (txtpassword.getText().isEmpty()){
            fucntion.showAlert(Alert.AlertType.ERROR, owner, "Please provide valid password", "Form Error");
            return;
        }
        
        String name = txtusername.getText();
        String pass = fucntion.encryptedPass(txtpassword.getText());
        
        boolean flag = validate(name, pass);
        if(!flag){
            fucntion.infoBox("Please enter correct username and password", null, owner ,"Failed");
            txtpassword.setText("");
            txtpassword.requestFocus();
        }else{
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            fucntion.infoBox("Login Success", null, owner ,"Success");
            fucntion.openModelWindow("/possystem/Dashboard.fxml", "POS | Welcome ", true);
        }

    }
    
    @FXML
    private void btnLogin(ActionEvent event) throws ClassNotFoundException { 
          login(event);
    }
    @FXML
    private void btnClose(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onEnter(ActionEvent event) throws ClassNotFoundException {
        btnLogin(event);
    }
}
