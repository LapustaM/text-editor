package com.example.texteditorproject.strategy;

import java.io.File;
import java.nio.file.Files;


public class TextFileDecoder {
    private EncodingStrategy strategy;

    public void setStrategy(EncodingStrategy strategy) {
        this.strategy = strategy;
    }

    public String decodeFile(File file, String encoding) throws Exception {
        switch (encoding.toLowerCase()) {
            case "utf-8" -> strategy = new UTF8EncodingStrategy();
            case "iso-8859-1" -> strategy = new ISO88591EncodingStrategy();
            case "windows-1252" -> strategy = new Windows1252EncodingStrategy();
            default -> throw new IllegalArgumentException("Unsupported encoding: " + encoding);
        }
        byte[] fileData = Files.readAllBytes(file.toPath());
        return strategy.decode(fileData);
    }
}
