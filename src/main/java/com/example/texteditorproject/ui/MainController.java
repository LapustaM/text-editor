package com.example.texteditorproject.ui;

import com.example.texteditorproject.command.*;
import com.example.texteditorproject.service.FileService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainController {
    @FXML
    public TextArea textArea;
    @FXML
    public Button copyButton, pasteButton, cutButton;

    private CommandInvoker invoker;

    private final FileService fileService = new FileService();
    private File currentFile;

    public void initialize() {
        TextEditorReceiver receiver = new TextEditorReceiver(textArea);
        invoker = new CommandInvoker();

        Command copyCommand = new CopyCommand(receiver);
        Command pasteCommand = new PasteCommand(receiver);
        Command cutCommand = new CutCommand(receiver);

        copyButton.setOnAction(e -> invoker.executeCommand(copyCommand));
        pasteButton.setOnAction(e -> invoker.executeCommand(pasteCommand));
        cutButton.setOnAction(e -> invoker.executeCommand(cutCommand));
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
}