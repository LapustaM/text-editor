package com.example.texteditorproject.command;

import javafx.scene.control.TextArea;

public class PasteCommand implements Command {
    private final TextArea textArea;

    public PasteCommand(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void execute() {
        String clipboardText = javafx.scene.input.Clipboard.getSystemClipboard().getString();
        if (clipboardText != null) {
            textArea.insertText(textArea.getCaretPosition(), clipboardText);
        }
    }
}
