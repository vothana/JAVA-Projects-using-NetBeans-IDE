/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package possystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class QuantityPopupController implements Initializable {

    @FXML
    private AnchorPane popup;
    @FXML
    private Button button;
    @FXML
    private StackPane ssss;
    @FXML
    private TextField txtDiscount;
    
    private int discountPrice;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        // TODO
        txtDiscount.requestFocus();
        
        ssss.setOpacity(0);
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(popup);
        tr.setDuration(Duration.millis(1));
        tr.setByY(800);
        tr.setOnFinished(e -> {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(popup);
            translate.setDuration(Duration.millis(500));
            translate.setByY(-800);
            translate.play();
            ssss.setOpacity(1);
        });
        tr.play();

        
        // fade
        FadeTransition fade = new FadeTransition();
        fade.setNode(ssss);
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
        scale.setByX(1.1);
        scale.setByY(1.04);
        scale.play();
        
//        
//        Timeline timeline = new Timeline();
//        Window stage = popup.getScene().getWindow();
//        
//        KeyFrame key = new KeyFrame(Duration.millis(30),
//                       new KeyValue (stage.getScene().getRoot().opacityProperty(), 0)); 
//        timeline.getKeyFrames().add(key); 
//        timeline.play();
    }
    
   @FXML
    private void goback(ActionEvent event) throws IOException {
        
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        
        TranslateTransition translate = new TranslateTransition();
        
        translate.setNode(popup);
        translate.setDuration(Duration.millis(500));
        translate.setByY(800);
        
        Timeline timeline = new Timeline();
        KeyFrame key = new KeyFrame(Duration.millis(300),
                       new KeyValue (stage.getScene().getRoot().opacityProperty(), 0)); 
        timeline.getKeyFrames().add(key);   
        timeline.setOnFinished((ae) -> stage.close()); 
        
        
        timeline.play();
        translate.play();

        if (txtDiscount.getText() != "")
            discountPrice = Integer.valueOf(txtDiscount.getText());
        
    }
    
    public int discountPrice(){
        return discountPrice;
    }
    
 
}
