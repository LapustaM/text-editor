package com.example.texteditorproject.model;

public class Snippet {
    private int id;
    private String name;
    private String content;

    public Snippet(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
