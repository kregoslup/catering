import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.SeedData;

public class App extends Application {

    private final String INITIAL_SCENE = "orders.fxml";

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SeedData.seedDatabase();
        Parent root = FXMLLoader.load(getClass().getResource((INITIAL_SCENE)));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
