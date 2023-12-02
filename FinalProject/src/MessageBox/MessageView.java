/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MessageBox;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Vothana CHY
 */
public class MessageView {
        FXMLLoader loader;
        Parent root;
        
        public void messageBox(Button btn, String message, String type ) throws IOException{
            loader = new FXMLLoader(getClass().getResource("/MessageBox/MessageSlider.fxml"));
            root = loader.load();
            
            MessageSliderController messageSliderController = loader.getController();
            Scene scene = btn.getScene();
            StackPane parentContainer = (StackPane) scene.getRoot();
            parentContainer.getChildren().add(root);
            
            messageSliderController.setValues(message, type);
        }
        
}
