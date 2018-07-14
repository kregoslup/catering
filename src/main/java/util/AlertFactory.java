package util;

import javafx.scene.control.Alert;

public class AlertFactory {

    /**
     * Shows alert with given header and message
     *
     * @param header Header of the alert to show
     * @param message Message of the alert
     */
    public static void showInfoAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
