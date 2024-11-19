package com.example.texteditorproject.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService implements TextEditorService{
    @Override
    public void performOperation() {

    }
    public String readFile(File file) {
        try {
            return Files.readString(Path.of(file.toURI()));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to read file.";
        }
    }
}
