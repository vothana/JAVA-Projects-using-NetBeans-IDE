/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package AboutUS;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import possystem.Slider;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class AboutUSController implements Initializable {

    @FXML
    private Rectangle rectabgleAnimateThree;
    @FXML
    private Rectangle rectabgleAnimateTwo;
    @FXML
    private Rectangle rectabgleAnimateOne;
    @FXML
    private Button btnBack;
    Slider silder = new Slider();
    FXMLLoader loader;
    Parent root;
    @FXML
    private AnchorPane anchorPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/possystem/Dashboard.fxml"));
        root = loader.load();
        silder.slideRightToLeft(loader, root , anchorPane , btnBack, true);
    }
    
}
