package possystem;

import MessageBox.Message;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class DashboardController implements Initializable {

    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button btnOrder;
    private Button btnCategory;

    Slider silder = new Slider();
    FXMLLoader loader;
    Parent root;
    @FXML
    private Button btnEmployee;
    @FXML
    private Button btnInventory;
    @FXML
    private Button btnCustomerView;
    @FXML
    private Button btnAboutUs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void exitApplication(ActionEvent event) throws IOException, InterruptedException {
        MessageBox.Message message = new Message();
        Color backgroundColor = backgroundColor = Color.rgb(133,234, 158, 0.3);
        String messagetext = "Do you wan to exit the Application ?";
        int ok = message.popupMessage(anchorpane, messagetext, "Confirm");
        if(ok>0){
            
            anchorpane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
            FadeTransition fade = new FadeTransition();
            fade.setNode(parentContainer);
            fade.setDuration(Duration.millis(400));
            fade.setInterpolator(Interpolator.LINEAR);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.setOnFinished(e -> {
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            });
            fade.play();
        }
    }

    @FXML
    private void orderDashboard(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/Orders/OrderDashboard.fxml"));
        root = loader.load();
        silder.slideBottomToTop(loader, root , anchorpane , btnOrder, true);
    }

    private void showCategory(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/Categories/Categories.fxml"));
        root = loader.load();
        silder.slideBottomToTop(loader, root , anchorpane , btnCategory, false);
    }

    @FXML
    private void employeeView(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/Employees/Employees.fxml"));
        root = loader.load();
        silder.slideLeftToRight(loader, root , anchorpane , btnEmployee, true);
    }

    @FXML
    private void productView(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/Products/Products.fxml"));
        root = loader.load();
        silder.slideLeftToRight(loader, root , anchorpane , btnInventory, true);
    }

    @FXML
    private void CustomerView(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/Customers/Customers.fxml"));
        root = loader.load();
        silder.slideLeftToRight(loader, root , anchorpane , btnInventory, true);
    }
    
    private void testtest(ActionEvent event) throws IOException, InterruptedException {
        MessageBox.Message message = new Message();
        String messagetext = "Please contact the service !";
        System.out.print(message.inputBox(anchorpane, messagetext, "Input"));
    }

    @FXML
    private void aboutUs(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/AboutUS/AboutUS.fxml"));
        root = loader.load();
        silder.slideRightToLeft(loader, root , anchorpane , btnAboutUs, true);
    }
}
