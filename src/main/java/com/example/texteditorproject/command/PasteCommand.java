package com.example.texteditorproject.command;

public class PasteCommand implements Command {
    private final TextEditorReceiver receiver;

    public PasteCommand(TextEditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.paste();
    }
}
