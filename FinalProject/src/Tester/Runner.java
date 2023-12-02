package Tester;

import possystem.Fucntions;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class Runner extends Application {

    private possystem.Fucntions fucntion = new Fucntions();

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //fucntion.openModelWindow("/Demo/Scene1.fxml", "RUNNER FOR TESTER", false);
            fucntion.openModelWindow("/possystem/Dashboard.fxml", "RUNNER FOR TESTER", true);
            //fucntion.openModelWindow("/MessageBox/MessageSlider.fxml", "RUNNER FOR TESTER", false);

            //fucntion.openModelWindow("/Products/Products.fxml", "RUNNER FOR TESTER", false);

            //fucntion.openModelWindow("/Orders/Table/Table.fxml", "RUNNER FOR TESTER", false);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//            Parent parentRoot = FXMLLoader.load(getClass().getResource("TestCRUD.fxml"));
//            Scene scene = new Scene(parentRoot);
//            primaryStage.setTitle("CRUD");
//            primaryStage.setResizable(false);
//            primaryStage.setIconified(false);
//            primaryStage.setScene(scene);
//            primaryStage.show();
