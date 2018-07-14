package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StageUtil {
    /**
     * Changes current view and controller to given resource.
     *
     * @param resource Resource of a view to change
     * @param control Javafx control used to get current scene
     * @param klazz Class used for loading given resource
     */
    public static void changeStage(String resource, Control control, Object klazz) {
        try {
            Stage stage = (Stage) control.getScene().getWindow();
            TitledPane root = (TitledPane) FXMLLoader.load(klazz.getClass().getClassLoader().getResource(resource));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
