package com.example.texteditorproject.template;

import java.io.FileWriter;
import java.io.IOException;

public class JSONExporter extends TextExporter {

    @Override
    protected String convertToFormat(String content) {
        return "{\"content\": \"" + content.replace("\"", "\\\"") + "\"}";
    }

    @Override
    protected void writeToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath + ".json")) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл JSON: " + e.getMessage());
        }
    }
}