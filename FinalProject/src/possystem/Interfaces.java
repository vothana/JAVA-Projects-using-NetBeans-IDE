package possystem;

import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 * @author Vothana CHY
 */
public interface Interfaces {
    void openModelWindow(String resource, String title, Boolean borderNone);
    void printSQLException (SQLException ex);
    void showAlert(Alert. AlertType alertType, Window owner,String message, String title);
    void infoBox(String infoMessage, String headerText,Window owner, String title);
    String encryptedPass(String pass);
}
