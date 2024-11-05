package com.example.texteditorproject.model;

public class Bookmark {
    private int id;
    private String filePath;
    private String name;
    private int lineNumber;

    public Bookmark(int id, String filePath, String name, int lineNumber) {
        this.id = id;
        this.filePath = filePath;
        this.name = name;
        this.lineNumber = lineNumber;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
