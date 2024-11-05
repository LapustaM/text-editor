package com.example.texteditorproject.strategy;

public interface EncodingStrategy {
    String readFile(String filePath);
    void writeFile(String filePath, String content);
}
