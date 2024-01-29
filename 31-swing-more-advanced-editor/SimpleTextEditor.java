import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;

/**
 * SimpleTextEditor extends JFrame, providing a basic text editing application.
 */
public class SimpleTextEditor extends JFrame {

    
    // Class Fields
    UndoManager undoManager = new UndoManager(); // UndoManager to handle undo and redo operations.
    JTextArea textArea; // Main text area for the editor.
    JMenuBar menuBar; // Top menu bar of the editor.
    JMenu fileMenu, helpMenu; // Menus for file operations and help.
    JMenuItem newItem, openItem, saveItem, exitItem, undoItem, redoItem, aboutItem; // Menu items for file operations and other functionalities.
    JButton saveButton, openButton, newButton; // Buttons for saving, opening, and creating new documents.
    JFileChooser fileDialog; // File chooser for open and save operations.
    String currentFile; // String to hold the path of the current file.
    JDialog confirmationDialog; // Dialog for confirming exit actions.
    JComboBox<String> fontChoice, fontSizeChoice, fontColorChoice; // Combo boxes for font, font size, and font color selection.
    JLabel statusBar; // Status bar to display document information like word count.
    JComboBox<String> bgColorChoice, fgColorChoice; // Combo boxes for background and foreground color selection.
    Highlighter highlighter; // Highlighter for the text area.
    JList<String> lineNumbers; // List to display line numbers.
    DefaultListModel<String> lineNumberModel; // Model for the line number list.
    int lastSearchIndex = 0; // Index to keep track of the last search position for 'Find' functionality.

    /**
    *  Constructor to initialize the SimpleTextEditor.
    */
    public SimpleTextEditor() {
        super("Simple Text Editor");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeComponents();
        // Listener to confirm before exiting the application.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                confirmAndExit();
            }
        });
        setVisible(true);
    }

    /**
     * Initializes all the components of the text editor.
     */
    private void initializeComponents() {
        setupTextArea();
        setupMenuBar();
        setupButtons();
        setupStatusBar();
        setupToolBar();
        setupFileDialog();
        setupEditMenu();
        setupHighlighter();
        setupDragAndDrop();
        setupKeyboardShortcuts();
    }

    /**
     * Sets up the main text area of the editor.
     */
    private void setupTextArea() {
        textArea = new JTextArea();
        lineNumberModel = new DefaultListModel<>();
        lineNumbers = new JList<>(lineNumberModel);
        lineNumbers.setFixedCellWidth(50);
        
        // Listener to update the status bar on text change.
        textArea.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                updateStatusBar();
            }
        });

        // Adding undoable edit listener for undo/redo functionality.
        textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));

        // Document listener to update line numbers on text change.
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateLineNumbers();
            }
            public void removeUpdate(DocumentEvent e) {
                updateLineNumbers();
            }
            public void changedUpdate(DocumentEvent e) {
                updateLineNumbers();
            }
        });

        // Wrapping textArea in a JScrollPane and adding line numbers.
        JScrollPane scrollPane = new JScrollPane(textArea); // Wrap textArea in a scroll pane
        scrollPane.setRowHeaderView(lineNumbers); // Add line numbers to the scroll pane
        add(scrollPane, BorderLayout.CENTER); // Change to scrollPane
    }

    /**
    * Sets up the menu bar with file and help menus.
    */
    private void setupMenuBar() {
        menuBar = new JMenuBar();
        setupFileMenu();
        setupHelpMenu();
        setJMenuBar(menuBar); // Change to setJMenuBar
    }

    /**
     * Sets up the file menu with items for new, open, save, and exit operations.
    */
    private void setupFileMenu() {
        fileMenu = new JMenu("File");

        // Menu items
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        // Adding action listeners for menu items.
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

    /**
    * Sets up the 'Help' menu in the menu bar.
    */
    private void setupHelpMenu() {
        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");

        // Action listener to show the 'About' dialog.
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);
    }

    /**
     * Sets up the buttons for new, open, and save operations.
    */
    private void setupButtons() {
        JPanel buttonPanel = new JPanel();

        // New button setup with an action listener to clear the text area.
        newButton = new JButton("New");
        newButton.addActionListener(e -> clearText());
        buttonPanel.add(newButton);

        // Open button setup with an action listener to open a file.
        openButton = new JButton("Open");
        openButton.addActionListener(e -> openFile());
        buttonPanel.add(openButton);

        // Save button setup with an action listener to save the current document.
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveFile());
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.PAGE_END);
    }

    /**
    * Sets up the toolbar with font and color options, and undo/redo buttons.
    */
    private void setupToolBar() {
        // Create a panel to hold font and color options
        JPanel fontAndColorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Setting background color options in a drop-down menu.
        bgColorChoice = new JComboBox<>(new String[]{"White", "Light Gray", "Yellow"});
        bgColorChoice.addActionListener(e -> changeBackgroundColor());
        fontAndColorPanel.add(new JLabel("Background Color:"));
        fontAndColorPanel.add(bgColorChoice);

        // Setting up font options in a drop-down menu.
        fontChoice = new JComboBox<>(new String[]{"Serif", "SansSerif", "Arial"});
        fontChoice.addActionListener(e -> fontChanged());
        fontAndColorPanel.add(new JLabel("Font:"));
        fontAndColorPanel.add(fontChoice);

        // Setting up font size options in a drop-down menu.
        fontSizeChoice = new JComboBox<>(new String[]{"12", "14", "16", "18", "20"});
        fontSizeChoice.addActionListener(e -> fontChanged());
        fontAndColorPanel.add(new JLabel("Size:"));
        fontAndColorPanel.add(fontSizeChoice);

        // Setting up font color options in a drop-down menu.
        fontColorChoice = new JComboBox<>(new String[]{"Black", "Red", "Blue"});
        fontColorChoice.addActionListener(e -> fontChanged());
        fontAndColorPanel.add(new JLabel("Color:"));
        fontAndColorPanel.add(fontColorChoice);

        // Undo button with an icon and an action listener for undo operation.
        ImageIcon undoIcon = createImageIcon("/resources/undo.png", "Undo");
        JButton undoButton = new JButton(undoIcon);
        undoButton.addActionListener(e -> undo());
        fontAndColorPanel.add(undoButton);

        // Redo button with an icon and an action listener for redo operation.
        ImageIcon redoIcon = createImageIcon("/resources/redo.png", "Redo");
        JButton redoButton = new JButton(redoIcon);
        redoButton.addActionListener(e -> redo());
        fontAndColorPanel.add(redoButton);

        // Add the font and color options panel to the top of the container
        add(fontAndColorPanel, BorderLayout.PAGE_START);
    }

    /**
     * Creates an ImageIcon from a given path and description.
     * @param path The path to the image resource.
     * @param description A short description of the image.
     * @return ImageIcon if the path is valid, otherwise null.
    */
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
    * Sets up the status bar to display word and character count.
    */
    private void setupStatusBar() {
        statusBar = new JLabel("Words: 0 Characters: 0");
        add(statusBar, BorderLayout.PAGE_END);
    }

    /**
    * Initializes the file chooser dialog for open and save operations.
    */      
    private void setupFileDialog() {
        fileDialog = new JFileChooser();
    }

    /**
    * Sets up the 'Edit' menu in the menu bar with a 'Find' option.
    */
    private void setupEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        JMenuItem findItem = new JMenuItem("Find");
        findItem.addActionListener(e -> showFindDialog());
        editMenu.add(findItem);
        menuBar.add(editMenu);
    }

    /**
    * Configures the text area highlighter for the 'Find' functionality.
    */
    private void setupHighlighter() {
        highlighter = textArea.getHighlighter();
        // If you need to configure the DefaultHighlighter specifically:
        if (highlighter instanceof DefaultHighlighter) {
            ((DefaultHighlighter) highlighter).setDrawsLayeredHighlights(false);
        }
    }
    
    /**
    * Changes the background color of the text area based on the selected color.
    */
    private void changeBackgroundColor() {
        Color bgColor = getColorFromString((String) bgColorChoice.getSelectedItem());
        textArea.setBackground(bgColor);
    }

    /**
     * Converts a string representation of a color to the corresponding Color object.
     * @param color String representing the color.
     * @return Color object corresponding to the provided string.
    */
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

    /**
    * Displays an 'About' dialog for the text editor.
    */
    private void showAboutDialog() {
        // Dialog in Swing
        confirmationDialog = new JDialog(this, "About", true);
        confirmationDialog.setLayout(new FlowLayout());
        confirmationDialog.add(new JLabel("Simple Text Editor - A Java Swing Example"));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> confirmationDialog.setVisible(false));
        confirmationDialog.add(okButton);
        confirmationDialog.setSize(300, 100);
        confirmationDialog.setVisible(true);
    }

    /**
    * Performs an undo operation using the undo manager.
    */
    private void undo() {
        try {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } catch (CannotUndoException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Performs a redo operation using the undo manager.
    */
    private void redo() {
        try {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        } catch (CannotRedoException ex) {
            ex.printStackTrace(); // Prints stack trace for debugging if redo operation fails.
        }
    }
    
    /**
     * Updates the status bar to show the current word and character count.
     */
    private void updateStatusBar() {
        String text = textArea.getText();
        int words = text.isEmpty() ? 0 : text.split("\\s+").length; // Counts words by splitting the text at whitespace.
        int characters = text.length();  // Counts total characters.
        statusBar.setText("Words: " + words + " Characters: " + characters);
    }

    /**
    * Changes the font of the text area based on selected font, size, and color.
    */
    private void fontChanged() {
        String fontName = (String) fontChoice.getSelectedItem();
        int fontSize = Integer.parseInt((String) fontSizeChoice.getSelectedItem());
        Color fontColor = getColorFromString((String) fontColorChoice.getSelectedItem());

        Font font = new Font(fontName, Font.PLAIN, fontSize);
        textArea.setFont(font);
        textArea.setForeground(fontColor);
    }

    /**
    * Clears the text area and resets the current file path.
    */
    private void clearText() {
        textArea.setText("");
        currentFile = null;
    }


    /**
     * Shows the 'Find' dialog allowing users to search for text within the text area.
     */
    private void showFindDialog() {
        JDialog findDialog = new JDialog(this, "Find", false);
        findDialog.setLayout(new FlowLayout());
        JTextField findField = new JTextField(20);
        JButton findNextButton = new JButton("Find Next");
        JButton cancelButton = new JButton("Cancel");
    
        findNextButton.addActionListener(e -> findNext(findField.getText()));
        cancelButton.addActionListener(e -> {
            clearHighlights(); // Clear highlights before closing
            findDialog.dispose();
        });
    
        findDialog.add(new JLabel("Find:"));
        findDialog.add(findField);
        findDialog.add(findNextButton);
        findDialog.add(cancelButton);
        findDialog.pack();
    
        // Add window listener to clear highlights when the dialog is closing
        findDialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                clearHighlights(); // Clears highlights when the dialog is closed.
            }
        });
        
        findDialog.setLocationRelativeTo(this);   // Sets the location of the dialog relative to the editor window, it will be displayed in center.
        findDialog.setVisible(true);
    }
    

    /**
     * Clears all the highlights in the text area.
     */
    private void clearHighlights() {
        highlighter.removeAllHighlights();
        lastSearchIndex = 0;
    }
    
    /**
     * Finds and highlights the next occurrence of a specified text string.
     * @param textToFind The text string to find in the text area.
    */
    private void findNext(String textToFind) {
        if (textToFind == null || textToFind.isEmpty()) {
            return; // Exits if there is no text to find.
        }
    
        String content = textArea.getText();
        int index = content.indexOf(textToFind, lastSearchIndex);
    
        if (index != -1) {
            textArea.setCaretPosition(index + textToFind.length());
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
            try {
                highlighter.addHighlight(index, index + textToFind.length(), painter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            lastSearchIndex = index + 1;
        } else {
            JOptionPane.showMessageDialog(this, "Text not found", "Find", JOptionPane.INFORMATION_MESSAGE);
            lastSearchIndex = 0; // Resets the search index if the word is not found.
        }
    }

    /**
     * Updates the line numbers in the line number panel to match the text area's content.
     */
    private void updateLineNumbers() {
        SwingUtilities.invokeLater(() -> {
            lineNumberModel.clear();
            int totalLines = textArea.getLineCount();
            for (int i = 1; i <= totalLines; i++) {
                lineNumberModel.addElement(String.valueOf(i));
            }
        });
    }

    /**
     * Opens a dialog for file selection and loads the selected file into the text area.
     */
    private void openFile() {
        int returnVal = fileDialog.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileDialog.getSelectedFile();
            currentFile = file.getAbsolutePath();
            readFile(currentFile);
        }
    }

    /**
     * Reads the content from the file at the given path and displays it in the text area.
     * @param filePath The absolute path of the file to be read.
    */
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
            JOptionPane.showMessageDialog(this, "File not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Saves the current text in the text area to the file.
     * If the file does not exist, a save dialog is shown to select a file.
     */
    private void saveFile() {
        if (currentFile == null) {
            int returnVal = fileDialog.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileDialog.getSelectedFile();
                currentFile = file.getAbsolutePath();
            }
        }
        if (currentFile != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile));
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error writing file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Sets up drag and drop functionality for the text area, allowing files to be dropped into it.
     */
    @SuppressWarnings("unchecked")
    private void setupDragAndDrop() {
        DropTargetListener dropTargetListener = new DropTargetAdapter() {
            public void drop(DropTargetDropEvent event) {
                try {
                    // Accept the drop first
                    event.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transferable = event.getTransferable();

                    // We expect file list
                    if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        java.util.List<File> fileList = (java.util.List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        if (!fileList.isEmpty()) {
                            // Load the content of the first file
                            File file = fileList.get(0);
                            if (file.isFile()) {
                                readFile(file.getAbsolutePath());
                            }
                        }
                    }

                    event.dropComplete(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    event.dropComplete(false);
                }
            }
        };

        new DropTarget(textArea, dropTargetListener);
    }

    /**
     * Sets up keyboard shortcuts for various text editing actions.
     */
    // Defining and setting keyboard shortcuts for cut, copy, paste, select all, save, undo, and redo actions.
    // The key bindings are associated with specific actions in the text area.
    private void setupKeyboardShortcuts() {
        // Cut
        KeyStroke cutKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        textArea.getInputMap().put(cutKeyStroke, DefaultEditorKit.cutAction);
    
        // Copy
        KeyStroke copyKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
        textArea.getInputMap().put(copyKeyStroke, DefaultEditorKit.copyAction);
    
        // Paste
        KeyStroke pasteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK);
        textArea.getInputMap().put(pasteKeyStroke, DefaultEditorKit.pasteAction);
    
        // Select All
        KeyStroke selectAllKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK);
        textArea.getInputMap().put(selectAllKeyStroke, DefaultEditorKit.selectAllAction);
    
        // Save
        KeyStroke saveKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        textArea.getInputMap().put(saveKeyStroke, "Save");
        textArea.getActionMap().put("Save", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        // Undo (Ctrl+Z)
        KeyStroke undoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK);
        textArea.getInputMap(JComponent.WHEN_FOCUSED).put(undoKeyStroke, "Undo");
        textArea.getActionMap().put("Undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });

        // Redo (Ctrl+Y)
        KeyStroke redoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK);
        textArea.getInputMap(JComponent.WHEN_FOCUSED).put(redoKeyStroke, "Redo");
        textArea.getActionMap().put("Redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redo();
            }
        });
    }

   /**
     * Displays a confirmation dialog when attempting to close the application.
     * Asks the user if they want to save changes before exiting.
     */
    private void confirmAndExit() {
        if (!textArea.getText().isEmpty()) {
            setupConfirmationDialog();
            confirmationDialog.setVisible(true);
        } else {
            System.exit(0);
        }
    }

    /**
     * Sets up a confirmation dialog with options to save changes, exit without saving, or cancel the exit.
     */
    private void setupConfirmationDialog() {
        confirmationDialog = new JDialog(this, "Confirm Exit", true);
        confirmationDialog.setLayout(new FlowLayout());
        confirmationDialog.setSize(300, 100);
        confirmationDialog.setLocationRelativeTo(this); 
    
        JLabel confirmLabel = new JLabel("Do you want to save changes before exiting?");
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        JButton cancelButton = new JButton("Cancel");
    
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
    
    /**
     * Main method to launch the SimpleTextEditor application.
     * @param args Command line arguments (not used).
     */    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            SimpleTextEditor editor = new SimpleTextEditor();
            editor.fontChoice.setSelectedItem("Arial");
            editor.fontSizeChoice.setSelectedItem("12");
            editor.fontColorChoice.setSelectedItem("Black");
        });
    }
}