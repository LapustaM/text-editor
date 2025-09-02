package com.example.texteditorproject.template;

import java.io.FileWriter;
import java.io.IOException;

public class TXTExporter extends TextExporter {

    @Override
    protected String convertToFormat(String content) {
        return content;
    }

    @Override
    protected void writeToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath + ".txt")) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Error exporting to TXT " + e.getMessage());
        }
    }
}