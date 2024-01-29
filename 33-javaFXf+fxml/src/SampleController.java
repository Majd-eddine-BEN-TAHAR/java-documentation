import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {

    @FXML // Annotation to mark the FXML components
    private TextField inputField; // The text field for user input

    @FXML
    private Label outputLabel; // The label for displaying text

    @FXML
    private void handleSubmit() {
        // Method to handle the button click action
        String inputText = inputField.getText(); // Get text from inputField
        outputLabel.setText("You entered: " + inputText); // Set text of outputLabel
    }
}