package com.example.texteditorproject.model;

import java.util.List;

public class Document {
    private String filePath;
    private String content;
    private String encoding;
    private List<Bookmark> bookmarks;
    private List<Macro> macros;

    public Document(String filePath, String encoding) {
        this.filePath = filePath;
        this.encoding = encoding;
    }

    // Getters and Setters
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public List<Macro> getMacros() {
        return macros;
    }

    public void setMacros(List<Macro> macros) {
        this.macros = macros;
    }


    public void addBookmark(Bookmark bookmark) {
        this.bookmarks.add(bookmark);
    }


    public void addMacro(Macro macro) {
        this.macros.add(macro);
    }
}
