import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Stack;


public class SimpleTextEditor extends Frame {

    // Class Fields
    Stack<String> undoStack = new Stack<>();
    Stack<String> redoStack = new Stack<>();
    TextArea textArea;
    MenuBar menuBar;
    Menu fileMenu, helpMenu;
    MenuItem newItem, openItem, saveItem, exitItem, undoItem, redoItem, aboutItem;
    Button saveButton, openButton, newButton;
    FileDialog fileDialog;
    String currentFile;
    Dialog confirmationDialog;
    Choice fontChoice, fontSizeChoice, fontColorChoice;
    Label statusBar;
    Choice bgColorChoice, fgColorChoice;

    // Constructor
    public SimpleTextEditor() {
        super("Simple Text Editor");
        setSize(800, 400);
        setLayout(new BorderLayout());
        initializeComponents();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                confirmAndExit();
            }
        });
        setVisible(true);
    }

    // Initialization Methods
    private void initializeComponents() {
        setupTextArea();
        setupMenuBar();
        setupButtons();
        setupStatusBar();
        setupToolBar();
        setupFileDialog();
    }

    private void setupTextArea() {
        textArea = new TextArea();
        add(textArea, BorderLayout.CENTER);
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                undoStack.push(textArea.getText());
                redoStack.clear();
            }
            public void keyReleased(KeyEvent ke) {
                updateStatusBar();
            }
        });
    }

    private void setupMenuBar() {
        menuBar = new MenuBar();
        setupFileMenu();
        setupHelpMenu();
        setMenuBar(menuBar);
    }

    private void setupFileMenu() {
        fileMenu = new Menu("File");

        // Menu items
        newItem = new MenuItem("New");
        openItem = new MenuItem("Open");
        saveItem = new MenuItem("Save");
        exitItem = new MenuItem("Exit");

        newItem.addActionListener(e -> clearText());
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        exitItem.addActionListener(e -> confirmAndExit());

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        fileMenu.addSeparator();

        menuBar.add(fileMenu);
    }

    private void setupHelpMenu() {
        helpMenu = new Menu("Help");
        aboutItem = new MenuItem("About");

        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);
    }

    private void setupButtons() {
        Panel buttonPanel = new Panel();

        // New button
        newButton = new Button("New");
        newButton.addActionListener(e -> clearText());
        buttonPanel.add(newButton);

        // Open button
        openButton = new Button("Open");
        openButton.addActionListener(e -> openFile());
        buttonPanel.add(openButton);

        // Save button
        saveButton = new Button("Save");
        saveButton.addActionListener(e -> saveFile());
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupToolBar(){
        // Create a panel to hold font and color options
        Panel fontAndColorPanel = new Panel(new FlowLayout(FlowLayout.LEFT));

        // Background Color options
        bgColorChoice = new Choice();
        bgColorChoice.addItem("White");
        bgColorChoice.addItem("Light Gray");
        bgColorChoice.addItem("Yellow");
        
        bgColorChoice.addItemListener(e -> changeBackgroundColor());
        fontAndColorPanel.add(new Label("Background Color:"));
        fontAndColorPanel.add(bgColorChoice);
    
        // Font options
        fontChoice = new Choice();
        fontChoice.addItem("Serif");
        fontChoice.addItem("SansSerif");
        fontChoice.addItem("Arial");

        fontChoice.addItemListener(e -> fontChanged(e));
        fontAndColorPanel.add(new Label("Font:"));
        fontAndColorPanel.add(fontChoice);
    
        // Font Size options
        fontSizeChoice = new Choice();
        fontSizeChoice.addItem("12");
        fontSizeChoice.addItem("14");
        fontSizeChoice.addItem("16");
        fontSizeChoice.addItem("18");
        fontSizeChoice.addItem("20");
        
        fontSizeChoice.addItemListener(e -> fontChanged(e));
        fontAndColorPanel.add(new Label("Size:"));
        fontAndColorPanel.add(fontSizeChoice);
    
        // Font Color options
        fontColorChoice = new Choice();
        fontColorChoice.addItem("Black");
        fontColorChoice.addItem("Red");
        fontColorChoice.addItem("Blue");
        
        fontColorChoice.addItemListener(e -> fontChanged(e));
        fontAndColorPanel.add(new Label("Color:"));
        fontAndColorPanel.add(fontColorChoice);
    
        // Undo button
        Button undoButton = new Button("Undo");
        undoButton.addActionListener(e -> undo());
        fontAndColorPanel.add(undoButton);

        // Redo button
        Button redoButton = new Button("Redo");
        redoButton.addActionListener(e -> redo());
        fontAndColorPanel.add(redoButton);

        // Add the font and color options panel to the top of the container
        add(fontAndColorPanel, BorderLayout.NORTH);
    }

    private void setupStatusBar() {
        statusBar = new Label("Words: 0 Characters: 0");
        add(statusBar, BorderLayout.SOUTH);
    }


    private void setupFileDialog() {
        fileDialog = new FileDialog(this, "Select a File", FileDialog.LOAD);
    }

    // Utility Methods
    private void changeBackgroundColor() {
        Color bgColor = getColorFromString(bgColorChoice.getSelectedItem());
        textArea.setBackground(bgColor);
    }


    private Color getColorFromString(String color) {
        switch (color) {
            case "Black": return Color.BLACK;
            case "Red": return Color.RED;
            case "Blue": return Color.BLUE;
            case "White": return Color.WHITE;
            case "Light Gray": return Color.LIGHT_GRAY;
            case "Yellow": return Color.YELLOW;
            
            default: return Color.BLACK;
        }
    }

    private void showAboutDialog() {
        Dialog aboutDialog = new Dialog(this, "About", true);
        aboutDialog.setLayout(new FlowLayout());
        aboutDialog.add(new Label("Simple Text Editor - A Java AWT Example"));
        Button okButton = new Button("OK");
        okButton.addActionListener(e -> aboutDialog.setVisible(false));
        aboutDialog.add(okButton);
        aboutDialog.setSize(300, 100);
        aboutDialog.setVisible(true);
    }

    private void undo() {
        if (!undoStack.empty()) {
            String lastState = undoStack.pop();
            redoStack.push(textArea.getText());
            textArea.setText(lastState);
        }
    }

    private void redo() {
        if (!redoStack.empty()) {
            String nextState = redoStack.pop();
            undoStack.push(textArea.getText());
            textArea.setText(nextState);
        }
    }

    private void updateStatusBar() {
        String text = textArea.getText();
        int words = text.isEmpty() ? 0 : text.split("\\s+").length;
        int characters = text.length();
        statusBar.setText("Words: " + words + " Characters: " + characters);
    }

    private void fontChanged(ItemEvent ie) {
        String fontName = fontChoice.getSelectedItem();
        int fontSize = Integer.parseInt(fontSizeChoice.getSelectedItem());
        Color fontColor = getColorFromString(fontColorChoice.getSelectedItem());
    
        Font font = new Font(fontName, Font.PLAIN, fontSize);
        textArea.setFont(font);
        textArea.setForeground(fontColor);
    }

    private void clearText() {
        textArea.setText("");
        currentFile = null;
    }

    private void openFile() {
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setVisible(true);
        String file = fileDialog.getFile();
        if (file != null) {
            currentFile = fileDialog.getDirectory() + file;
            readFile(currentFile);
        }
    }

    private void readFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            textArea.setText("");
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("File not found: " + ex.getMessage());
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            fileDialog.setMode(FileDialog.SAVE);
            fileDialog.setVisible(true);
            String file = fileDialog.getFile();
            if (file != null) {
                currentFile = fileDialog.getDirectory() + file;
            }
        }
        if (currentFile != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile));
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException ex) {
                System.out.println("Error writing file: " + ex.getMessage());
            }
        }
    }

    private void confirmAndExit() {
        if (!textArea.getText().isEmpty()) {
            setupConfirmationDialog();
            confirmationDialog.setVisible(true);
        } else {
            System.exit(0);
        }
    }

    private void setupConfirmationDialog() {
        confirmationDialog = new Dialog(this, "Confirm Exit", true);
        confirmationDialog.setLayout(new FlowLayout());
        confirmationDialog.setSize(300, 100);

        Label confirmLabel = new Label("Do you want to save changes before exiting?", Label.CENTER);
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        Button cancelButton = new Button("Cancel");

        yesButton.addActionListener(e -> {
            saveFile();
            confirmationDialog.setVisible(false);
            System.exit(0);
        });

        noButton.addActionListener(e -> {
            confirmationDialog.setVisible(false);
            System.exit(0);
        });

        cancelButton.addActionListener(e -> confirmationDialog.setVisible(false));

        confirmationDialog.add(confirmLabel);
        confirmationDialog.add(yesButton);
        confirmationDialog.add(noButton);
        confirmationDialog.add(cancelButton);

        confirmationDialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                confirmationDialog.setVisible(false);
            }
        });
    }

    public static void main(String args[]) {
        SimpleTextEditor editor = new SimpleTextEditor();
        editor.fontChoice.select("Arial");
        editor.fontSizeChoice.select("12");
        editor.fontColorChoice.select("Black");
    }
}
