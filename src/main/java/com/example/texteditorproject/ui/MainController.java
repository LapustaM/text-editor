package com.example.texteditorproject.ui;

import com.example.texteditorproject.command.*;
import com.example.texteditorproject.model.Bookmark;
import com.example.texteditorproject.observer.EditorSubject;
import com.example.texteditorproject.observer.SyntaxHighlighter;
import com.example.texteditorproject.observer.SyntaxObserver;
import com.example.texteditorproject.service.BookmarkManager;
import com.example.texteditorproject.service.FileService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainController {
    @FXML
    public SplitPane splitPane;
    @FXML
    public TextArea textArea;
    @FXML
    public TextFlow textFlow;
    @FXML
    public VBox editorContainer;
    @FXML
    public Button copyButton, pasteButton, cutButton;
    @FXML
    public MenuItem syntaxHighlightMenuItem;

    private final EditorSubject editorSubject = new EditorSubject();
    private boolean isSyntaxViewVisible = false;

    private CommandInvoker invoker;

    private final FileService fileService = new FileService();
    private File currentFile;

    private final BookmarkManager bookmarkManager = new BookmarkManager();

    public void initialize() {
        TextEditorReceiver receiver = new TextEditorReceiver(textArea);
        invoker = new CommandInvoker();

        Command copyCommand = new CopyCommand(receiver);
        Command pasteCommand = new PasteCommand(receiver);
        Command cutCommand = new CutCommand(receiver);

        copyButton.setOnAction(e -> invoker.executeCommand(copyCommand));
        pasteButton.setOnAction(e -> invoker.executeCommand(pasteCommand));
        cutButton.setOnAction(e -> invoker.executeCommand(cutCommand));

        SyntaxObserver highlighter = new SyntaxHighlighter(textFlow);
        editorSubject.addObserver(highlighter);

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            editorSubject.notifyObservers(newValue);
        });

        splitPane.getItems().remove(textFlow);
        isSyntaxViewVisible = false;
        syntaxHighlightMenuItem.setText("Show Syntax Highlight Window");

        syntaxHighlightMenuItem.setOnAction(event -> toggleSyntaxView());
    }

    public void openFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        // choose file window
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            currentFile = file;
            String content = fileService.readFileWithEncoding(file, "utf-8");
            textArea.setText(content);
        }
    }

    public void chooseEncoding() {
        //alert if file not opened
        if (currentFile == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No File Opened");
            alert.setHeaderText(null);
            alert.setContentText("Please open a file before choosing an encoding.");
            alert.showAndWait();
            return;
        }

        // all encodings for now
        List<String> encodings = Arrays.asList("UTF-8", "ISO-8859-1", "Windows-1252");

        // choice dialog
        ChoiceDialog<String> dialog = new ChoiceDialog<>("UTF-8", encodings);
        dialog.setTitle("Choose Encoding");
        dialog.setHeaderText(null);
        dialog.setContentText("Select the encoding to read the file:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String selectedEncoding = result.get();
            try {
                //file reopens with new encoding
                String content = fileService.readFileWithEncoding(currentFile, selectedEncoding);
                textArea.setText(content);
            } catch (Exception e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to decode the file with the selected encoding.");
                errorAlert.showAndWait();
            }
        }

    }

    private void toggleSyntaxView() {
        if (isSyntaxViewVisible) {
            splitPane.getItems().remove(textFlow);
            syntaxHighlightMenuItem.setText("Show Syntax Highlight Window");
        } else {
            if (!splitPane.getItems().contains(textFlow)) {
                splitPane.getItems().add(textFlow);
            }
            syntaxHighlightMenuItem.setText("Hide Syntax Highlight Window");
        }
        isSyntaxViewVisible = !isSyntaxViewVisible;
    }
    public void createNewFile() {
        textArea.clear();
        currentFile = null;
    }

    public void saveFile() {
        if (currentFile == null) {
            saveFileAs();
        } else {
            try {
                fileService.writeFileWithEncoding(currentFile, textArea.getText(), "UTF-8");
            } catch (IOException e) {
                showErrorAlert("Error saving file", "Failed to save the file.");
            }
        }
    }

    public void saveFileAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                fileService.writeFileWithEncoding(file, textArea.getText(), "UTF-8");
                currentFile = file;
            } catch (IOException e) {
                showErrorAlert("Error saving file", "Failed to save the file.");
            }
        }
    }

    public void addBookmark() {
        if (currentFile == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No File Opened");
            alert.setHeaderText(null);
            alert.setContentText("Please open a file before adding a bookmark.");
            alert.showAndWait();
            return;
        }

        int cursorPosition = textArea.getCaretPosition();
        String selectedText = textArea.getSelectedText();

        if (selectedText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Text Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select some text to create a bookmark.");
            alert.showAndWait();
            return;
        }

        Bookmark bookmark = new Bookmark(currentFile.getAbsolutePath(), selectedText, cursorPosition);
        bookmarkManager.addBookmark(bookmark);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bookmark Added");
        alert.setHeaderText(null);
        alert.setContentText("Bookmark created: " + bookmark);
        alert.showAndWait();
    }

    public void viewBookmarks() {
        List<Bookmark> bookmarks = bookmarkManager.getAllBookmarks();

        if (bookmarks.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Bookmarks");
            alert.setHeaderText(null);
            alert.setContentText("You have no bookmarks.");
            alert.showAndWait();
            return;
        }

        ChoiceDialog<Bookmark> dialog = new ChoiceDialog<>(null, bookmarks);
        dialog.setTitle("Select Bookmark");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose a bookmark:");

        Optional<Bookmark> result = dialog.showAndWait();
        result.ifPresent(this::navigateToBookmark);
    }

    private void navigateToBookmark(Bookmark bookmark) {
        if (currentFile != null && currentFile.getAbsolutePath().equals(bookmark.getFilePath())) {
            textArea.positionCaret(bookmark.getPosition());
        } else {
            File file = new File(bookmark.getFilePath());
            if (!file.exists()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("File Not Found");
                alert.setHeaderText(null);
                alert.setContentText("The file associated with this bookmark does not exist.");
                alert.showAndWait();
                return;
            }

            try {
                String content = fileService.readFileWithEncoding(file, "utf-8");
                currentFile = file;
                textArea.setText(content);
                textArea.positionCaret(bookmark.getPosition());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to open the file associated with this bookmark.");
                alert.showAndWait();
            }
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

