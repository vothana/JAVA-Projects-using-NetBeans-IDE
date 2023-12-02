/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package possystem;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author Vothana CHY
 */
public class Fucntions implements Interfaces{
    Scene fxmlFile;
    Parent root;
    Stage window;
    
    
    @Override
    //manual print sql exception
    public void printSQLException(SQLException ex) {
        for (Throwable e: ex) { 
            if (ex instanceof SQLException){
                e.printStackTrace (System.err); 
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause ();
                    while(t != null) {
                         System.err.println("Cause: " + t); 
                         t = t.getCause();
                    }
            }
        }
    }

    @Override
    //alert if somthing wrong
    public void showAlert(Alert.AlertType alertType, Window owner, String message, String title) {
        Alert alert = new Alert (alertType);
        alert.setContentText (message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.initOwner(owner);
        alert.show();
    }

    @Override
    public void openModelWindow(String resource, String title, Boolean borderNone) {
        try{
            root = FXMLLoader.load(getClass().getResource(resource));
            fxmlFile = new Scene(root);
            window = new Stage();
            window.setScene(fxmlFile);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setAlwaysOnTop(true);
            window.setIconified(false);
            window.setResizable(false);
            window.getIcons().add(new Image("/images/Logo.png"));
            if( borderNone)
                window.initStyle(StageStyle.UNDECORATED);
            window.setTitle(title);
            window.showAndWait();
        }catch(IOException ex){
            System.err.print(ex.getMessage());
        }
    }

    @Override
    public void infoBox(String infoMessage, String headerText,Window owner, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle (title);
        alert.initOwner(owner);
        alert.setHeaderText (headerText);
        alert.showAndWait();
    }

    @Override
    public String encryptedPass(String password) { 
        String encryptedpassword = null;  
        try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(password.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)
        {  
            e.printStackTrace();  
        }  
          
        return encryptedpassword; 
    }

    
}
