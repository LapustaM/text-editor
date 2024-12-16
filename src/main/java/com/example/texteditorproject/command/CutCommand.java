package com.example.texteditorproject.command;

public class CutCommand implements Command {
    private final TextEditorReceiver receiver;

    public CutCommand(TextEditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cut();
    }
}