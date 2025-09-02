package com.example.texteditorproject.model;

import com.google.gson.annotations.Expose;

public class Bookmark {
    @Expose
    public String filePath;

    @Expose
    public String textSnippet;

    @Expose
    public int position;

    public Bookmark(String filePath, String textSnippet, int position) {
        this.filePath = filePath;
        this.textSnippet = textSnippet;
        this.position = position;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTextSnippet() {
        return textSnippet;
    }

    public void setTextSnippet(String textSnippet) {
        this.textSnippet = textSnippet;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    @Override
    public String toString() {
        return "..." + textSnippet + "...";
    }

}
