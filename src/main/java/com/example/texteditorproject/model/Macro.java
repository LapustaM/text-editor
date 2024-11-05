package com.example.texteditorproject.model;

public class Macro {
    private int id;
    private String name;
    private String actions; // Serialized list of actions or commands in this macro

    public Macro(int id, String name, String actions) {
        this.id = id;
        this.name = name;
        this.actions = actions;
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

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
}
