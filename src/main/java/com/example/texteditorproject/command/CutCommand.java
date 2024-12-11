package com.example.texteditorproject.command;

import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CutCommand implements Command {
    private final TextArea textArea;

    public CutCommand(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void execute() {
        String selectedText = textArea.getSelectedText();
        if (!selectedText.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);

            int start = textArea.getSelection().getStart();
            int end = textArea.getSelection().getEnd();
            textArea.deleteText(start, end);
        }
    }
}