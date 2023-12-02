/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package MessageBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class MessageController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private AnchorPane anchorpanpopup;
    private Button button;
    @FXML
    private Label message;
    @FXML
    private TextField txtField;
    @FXML
    private Button btnOK;
    @FXML
    private HBox confirmButtonContainer;
    @FXML
    private Button btnConfirmOk;
    @FXML
    private Button btnConfirmCancel;
    @FXML
    private HBox okBtnHbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void starter(){
        stackpane.setOpacity(0);
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(anchorpanpopup);
        tr.setDuration(Duration.millis(1));
        tr.setByY(700);
        tr.setOnFinished(e -> {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(anchorpanpopup);
            translate.setDuration(Duration.millis(500));
            translate.setByY(-700);
            translate.play();
            stackpane.setOpacity(1);
        });
        tr.play();

        
        // fade
        FadeTransition fade = new FadeTransition();
        fade.setNode(stackpane);
        fade.setDuration(Duration.millis(700));
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

         //scale
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(button);
        scale.setDuration(Duration.millis(500));
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setByX(0.5);
        scale.setByY(0.5);
        scale.play();
    }
    
    public void popUpBox(String message , String type) throws IOException, InterruptedException{
        this.message.setText(message);
        if("Confirm".equals(type)){
            starter();
            confirmButtonContainer.setVisible(true);
            okBtnHbox.setVisible(false);
        }else if("Input".equals(type)){
            starter();
            txtField.setVisible(true);
            TranslateTransition tr = new TranslateTransition();
            tr.setNode(this.message);
            tr.setByY(-23);
            tr.setDelay(Duration.millis(800));
            tr.setDuration(Duration.millis(300));
            tr.play();
        } else {
            back();
        }
    }
    
    private void back(){
        Stage stage = (Stage) anchorpanpopup.getScene().getWindow();
        
        TranslateTransition translate = new TranslateTransition();
        
        translate.setNode(anchorpanpopup);
        translate.setDuration(Duration.millis(500));
        translate.setByY(700);
        
        Timeline timeline = new Timeline();
        KeyFrame key = new KeyFrame(Duration.millis(300),
                       new KeyValue (stage.getScene().getRoot().opacityProperty(), 0)); 
        timeline.getKeyFrames().add(key);   
        timeline.setOnFinished((ae) -> stage.close()); 
        
        
        timeline.play();
        translate.play();
    }
    
    //return from btn clicked
    int confirm;
    public int getReturn(){
        return confirm;
    }
    
    //return text from field
    String text;
    public String getText(){
        return text;
    }
    
    @FXML
    private void goback(ActionEvent event) {
        back();
        text = txtField.getText();
    }
    
    @FXML
    private void confirmOK(ActionEvent event) {
        back();
        confirm = 1;
    }

    @FXML
    private void confirmCancel(ActionEvent event) {
        back();
        confirm = 0;
    }
    

    @FXML
    private void rotatesss(MouseEvent event) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(button);
        rotate.setDuration(Duration.millis(500));
        rotate.setByAngle(12);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.play();
    }

    @FXML
    private void rotatesssBack(MouseEvent event) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(button);
        rotate.setDuration(Duration.millis(500));
        rotate.setByAngle(-12);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.play();
    }
   
}
