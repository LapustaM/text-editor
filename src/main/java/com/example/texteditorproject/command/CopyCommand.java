package com.example.texteditorproject.command;

import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CopyCommand implements Command{
    private final TextArea textArea;

    public CopyCommand(TextArea textArea) {
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
        }
    }


}
