package Tester;

import possystem.Fucntions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class MainController implements Initializable {
    private Fucntions fucntion = new Fucntions();
    
    @FXML
    private Button btnCustomer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML //click btn to show fxml file in another package
    private void manageCustomer(ActionEvent event) {
        try{
            fucntion.openModelWindow("CRUD.fxml", "CRUD", false);
        }catch(Exception ex){
            System.err.print(ex.getMessage());
        }
    }
    
    
}
