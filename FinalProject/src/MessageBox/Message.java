/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MessageBox;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author Vothana CHY
 */
public class Message {
    Window owner;
    FXMLLoader loader;
    Parent root;
    Scene scene ;
    Stage primaryStage;
    MessageController fxml;
    public int popupMessage(AnchorPane anchorPane , String message, String type) throws IOException, InterruptedException {
        starter(anchorPane, message, type);
        blur(anchorPane, 0);
        return fxml.getReturn();
    }
    
    public String inputBox(AnchorPane anchorPane , String message, String type) throws IOException, InterruptedException {
        starter(anchorPane, message, type);
        blur(anchorPane, 0);
        return fxml.getText();
    }
    
    private void starter(AnchorPane anchorPane , String message, String type) throws IOException, InterruptedException{
        owner = anchorPane.getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("/MessageBox/Message.fxml"));
        root = loader.load();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.initOwner(owner);
        primaryStage.setScene(scene);
        primaryStage.focusedProperty();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        blur(anchorPane,10);
        fxml = loader.getController();
        fxml.popUpBox(message, type);
        primaryStage.showAndWait();
    }
    
    private void blur(AnchorPane anchorPane, int blurRadius){
         GaussianBlur g = new GaussianBlur();  
         g.setRadius(blurRadius); 
        anchorPane.setEffect(g);
    }
}



















        
//        ***using TextInputDialog***
//        TextInputDialog textInput = new TextInputDialog();
//        textInput.setTitle ("Text Input Dialog");
//        textInput.getDialogPane ().setContentText("First Name: ");
//        Window owner = txtaaa.getScene().getWindow();
//        textInput.initOwner(owner);
//        blur(10);
//        Optional<String> result = textInput.showAndWait();
//        TextField input= textInput.getEditor();
//
//        if(input.getText() != null && input.getText().length() != 0){
//           System.out.println("Input Text is: " + input.getText() );
//            txtaaa.setText(input.getText()) ;
//            blur(0);
//        }else{
//            System.out.println("User not entered anything");
//            blur(0);
//        }
