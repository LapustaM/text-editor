package com.example.texteditorproject.template;

public abstract class TextExporter {
    public final void export(String content, String filePath) {
        String formattedContent = convertToFormat(content);
        writeToFile(formattedContent, filePath);
    }
    protected abstract String convertToFormat(String content);
    protected abstract void writeToFile(String content, String filePath);
}
