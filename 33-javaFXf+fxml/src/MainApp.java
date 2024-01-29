import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Create a Scene containing the loaded FXML
        Scene scene = new Scene(root);

        // Add the external CSS file to the Scene
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        // Set the window title
        stage.setTitle("JavaFX and FXML Application");

        // Set the Scene on the Stage
        stage.setScene(scene);

        // Display the Stage
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the application
        launch(args);
    }
}