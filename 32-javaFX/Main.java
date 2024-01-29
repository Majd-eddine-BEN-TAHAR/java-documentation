import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.scene.text.Font;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main extends Application {

    private TextArea textArea;
    private Stage primaryStage;
    private ToolBar statusBar;
    private Label statusLabel;
    private ToggleButton boldButton, italicButton;
    private ColorPicker colorPicker;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Simple Text Editor");

        BorderPane root = new BorderPane();
        textArea = new TextArea();
        root.setCenter(textArea);

        MenuBar menuBar = new MenuBar();
        root.setTop(buildMenus(menuBar));

        HBox toolBar = buildToolBar();
        root.setBottom(toolBar);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar buildMenus(MenuBar menuBar) {
        Menu fileMenu = new Menu("File");
        MenuItem newFile = new MenuItem("New");
        newFile.setOnAction(e -> textArea.clear());
        MenuItem openFile = new MenuItem("Open");
        openFile.setOnAction(e -> openFile());
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setOnAction(e -> saveFile());
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));
        fileMenu.getItems().addAll(newFile, openFile, saveFile, new SeparatorMenuItem(), exit);

        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private HBox buildToolBar() {
        HBox toolBar = new HBox(10);
        toolBar.setPadding(new Insets(10));
        toolBar.setStyle("-fx-background-color: #EEEEEE;");

        Label fontSizeLabel = new Label("Font Size:");
        ComboBox<String> fontSizeComboBox = new ComboBox<>();
        fontSizeComboBox.getItems().addAll("10", "12", "14", "16", "18", "20");
        fontSizeComboBox.setValue("12");
        fontSizeComboBox.setOnAction(e -> textArea.setFont(new Font(Double.parseDouble(fontSizeComboBox.getValue()))));

        boldButton = new ToggleButton("Bold");
        boldButton.setOnAction(e -> toggleBold());
        italicButton = new ToggleButton("Italic");
        italicButton.setOnAction(e -> toggleItalic());

        colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> changeTextColor(colorPicker.getValue()));

        toolBar.getChildren().addAll(boldButton, italicButton, colorPicker);
        return toolBar;
    }

    private void toggleBold() {
        FontWeight weight = boldButton.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL;
        updateFont(weight, null);
    }

    private void toggleItalic() {
        FontPosture posture = italicButton.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR;
        updateFont(null, posture);
    }

    private void changeTextColor(Color color) {
        textArea.setStyle("-fx-text-fill: " + toRgbString(color) + ";");
    }

    private void updateFont(FontWeight weight, FontPosture posture) {
        Font currentFont = textArea.getFont();
        FontWeight finalWeight = weight != null ? weight : FontWeight.findByName(currentFont.getStyle());
        FontPosture finalPosture = posture != null ? posture : FontPosture.findByName(currentFont.getStyle());
        textArea.setFont(Font.font(currentFont.getFamily(), finalWeight, finalPosture, currentFont.getSize()));
    }

    private String toRgbString(Color c) {
        return String.format("rgb(%d, %d, %d)", 
            (int) (c.getRed() * 255), 
            (int) (c.getGreen() * 255), 
            (int) (c.getBlue() * 255));
    }


    private void updateStatus(String text) {
        int characters = text.length();
        int words = text.trim().isEmpty() ? 0 : text.split("\\s+").length;
        statusLabel.setText("Characters: " + characters + " Words: " + words);
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            readFile(file);
        }
    }

    private void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            textArea.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
