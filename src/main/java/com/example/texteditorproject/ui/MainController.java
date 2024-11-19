package com.example.texteditorproject.ui;

import com.example.texteditorproject.service.FileService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainController {
    @FXML
    public TextArea textArea;

    private final FileService fileService = new FileService();

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        // choose file window
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            String content = fileService.readFile(file);
            textArea.setText(content);
        }
    }
}