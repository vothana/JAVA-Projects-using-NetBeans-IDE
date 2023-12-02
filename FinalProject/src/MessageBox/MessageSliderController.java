/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package MessageBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class MessageSliderController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private HBox containerBox;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Circle circle;
    @FXML
    private Label messageText;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnOK;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //starter();
    }
    //private int returnValue;
    int returnValue;
    public void starter(){  
        TranslateTransition translate = new TranslateTransition();
        TranslateTransition translate1 = new TranslateTransition();
        TranslateTransition translate2 = new TranslateTransition();
            translate.setNode(anchorpane);
            translate.setDuration(Duration.millis(1));
            translate.setByX(400);
            translate.setOnFinished(e -> {
                translate1.setNode(anchorpane);
                translate1.setDuration(Duration.millis(1000));
                translate1.setByX(-400);
                translate1.setOnFinished(eh -> {
                    translate2.setNode(circle);
                    translate2.setDuration(Duration.millis(2000));
                    translate2.setByY(-84);
                    translate2.setOnFinished(el -> {
                            if(btnOK.isVisible()){
                               btnOK.setOnAction(eee -> {
                                    finalSlider();
                               });
                            }else{
                                 finalSlider();
                            }
                        });
                    translate2.play();
                });
                translate1.play();
            });
            translate.play();
    }

    @FXML
    private int clickedOK(ActionEvent event) {
         finalSlider();
         return 1;
    }
    
    private void finalSlider(){
        TranslateTransition translate3 = new TranslateTransition();
        TranslateTransition translate4 = new TranslateTransition();
            translate3.setNode(circle);
            translate3.setDuration(Duration.millis(1000));
            translate3.setByX(410);
            translate3.setOnFinished(el -> { 
                translate4.setNode(anchorpane);
                translate4.setDuration(Duration.millis(300));
                translate4.setByX(400);
                translate4.setOnFinished(eo -> {
                    Scene scene = anchorpane.getScene();
                    StackPane parentContainer = (StackPane) scene.getRoot();
                    parentContainer.getChildren().remove(stackpane);
                });
                translate4.play();
            });
            translate3.play();
    }
    
    private void labelTransform(){
        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(messageText);
        translate5.setDuration(Duration.millis(300));
        translate5.setByY(16);
        translate5.play();
    }
    
    public void setValues(String message , String type){
        
        File file = null; ;
        Color rectangleColor = null;
        Color backgroundColor = null;
        Color textFill = null;
     
        if("Wrong".equals(type)){
            rectangleColor = Color.BROWN;
            backgroundColor = Color.rgb(227,131, 129);
            textFill = Color.WHITE;
            file  = new File("src/images/Wrong.png");
            labelTransform();
        }else if("Confirm".equals(type)){
            rectangleColor = Color.rgb(28, 56, 121);
            backgroundColor = Color.rgb(96, 126, 170);
            textFill = Color.WHITE;
            file  = new File("src/images/Warning.png");
            btnOK.setVisible(true);
        }else{
            rectangleColor = Color.rgb(46,167,0);
            backgroundColor = Color.rgb(85,250,129);
            textFill = Color.BLACK;
            file  = new File("src/images/Correct.png");
            labelTransform();
        }
            
        containerBox.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        rectangle.setFill(rectangleColor);
        messageText.setText(message);
        messageText.setTextFill(textFill);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        
        starter();
    }
  
}
