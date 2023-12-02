package Login;

import javafx.application.Application;
import javafx.stage.Stage;
import possystem.Fucntions;

/**
 *
 * @author Vothana CHY
 */
public class Login extends Application {
    private static Fucntions src = new Fucntions();
    @Override
    public void start(Stage primaryStage) throws Exception {
        src.openModelWindow("/Login/Login.fxml", "", true);
    }
    public static void main(String[] args) {
        launch(args);
    }  
}