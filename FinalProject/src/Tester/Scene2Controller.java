/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Tester;

import possystem.Slider;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class Scene2Controller implements Initializable {

    @FXML
    private AnchorPane scene2;
    @FXML
    private Button button;
    @FXML
    private Label labelDisplayText;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoScene1(ActionEvent event) throws IOException {


        Slider silder = new Slider();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        Parent root = loader.load();
        silder.slideBottomToTop(loader, root , scene2 , button , false);
        //create new obj from class Scene2
        //Scene2Controller scene2 = loader.getController();
        //scene2.display(name.getText());
        
    }
    
    
    public void display(String text){
        labelDisplayText.setText(text);
    }
    
}
