package com.example.texteditorproject.command;

import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class TextEditorReceiver {
    private final TextArea textArea;

    public TextEditorReceiver(TextArea textArea) {
        this.textArea = textArea;
    }

    public void copy() {
        String selectedText = textArea.getSelectedText();
        if (!selectedText.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
        }
    }

    public void paste() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if (clipboard.hasString()) {
            textArea.insertText(textArea.getCaretPosition(), clipboard.getString());
        }
    }

    public void cut() {
        String selectedText = textArea.getSelectedText();
        if (!selectedText.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
            textArea.deleteText(textArea.getSelection());
        }
    }
}
