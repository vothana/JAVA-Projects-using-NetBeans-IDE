/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package possystem;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Vothana CHY
 */
public class Slider {
    
    
    
    ////////////////////////Slider Height
    public void slideBottomToTop(FXMLLoader loader, Parent root, AnchorPane anchorRoot, Button btn , boolean removeAnchorRoot) throws IOException{
        boolean remove;
        if (removeAnchorRoot)
            remove = true;
        else
            remove = false;
        sliderHeight(loader, root, anchorRoot, btn, false ,remove);
    }
    
    public void slideTopToBottom(FXMLLoader loader, Parent root, AnchorPane anchorRoot, Button btn , boolean removeAnchorRoot) throws IOException{
        boolean remove;
        if (removeAnchorRoot)
            remove = true;
        else
            remove = false;
        sliderHeight(loader, root, anchorRoot, btn, true , remove);
    }

    public void sliderHeight(FXMLLoader loader, Parent root, AnchorPane anchorRoot, Button btn , boolean...var ) throws IOException{
        Scene scene = btn.getScene();
        Double lenght;
        if (var[0])
            lenght = -scene.getHeight();
        else
            lenght = scene.getHeight();
        
        //Set Y of second scene to Height of window
        root.translateYProperty().set(lenght);
        //Add second scene. Now both first and second scene is present
        StackPane parentContainer = (StackPane) scene.getRoot();
        parentContainer.getChildren().add(root);
        
 
        //Create new TimeLine animation
        Timeline timeline = new Timeline();
        //Animate Y property
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        if (var[1]){
            //After completing animation, remove first scene
            timeline.setOnFinished(t -> {
                parentContainer.getChildren().remove(anchorRoot);
            });
        }
        timeline.play();
    }
    
    
    ////////////////////////Slider Width
    public void slideRightToLeft(FXMLLoader loader, Parent root, AnchorPane anchorRoot, Button btn , boolean removeAnchorRoot) throws IOException{
        boolean remove;
        if (removeAnchorRoot)
            remove = true;
        else
            remove = false;
            
        sliderWidth(loader, root, anchorRoot, btn, false , remove );
    }
    
    public void slideLeftToRight(FXMLLoader loader, Parent root, AnchorPane anchorRoot, Button btn, boolean removeAnchorRoot) throws IOException{
        boolean remove;
        if (removeAnchorRoot)
            remove = true;
        else
            remove = false;
        sliderWidth(loader, root, anchorRoot, btn, true ,remove );
    }

    public void sliderWidth(FXMLLoader loader, Parent root, AnchorPane anchorRoot, Button btn , boolean...var ) throws IOException{
        Scene scene = btn.getScene();
        Double lenght;
        
        if (var[0])
            lenght = -scene.getWidth();
        else
            lenght = scene.getWidth();
        
        //Set Y of second scene to Height of window
        root.translateXProperty().set(lenght);
        //Add second scene. Now both first and second scene is present
        StackPane parentContainer = (StackPane) scene.getRoot();
        parentContainer.getChildren().add(root);
 
        //Create new TimeLine animation
        Timeline timeline = new Timeline();
        //Animate Y property
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        
        if (var[1]){
            //After completing animation, remove first scene
            timeline.setOnFinished(t -> {
                parentContainer.getChildren().remove(anchorRoot);
            });
        }
            
        //play the slide
        timeline.play();
    }
}
