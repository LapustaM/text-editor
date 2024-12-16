package com.example.texteditorproject.command;

public class CopyCommand implements Command{
    private final TextEditorReceiver receiver;

    public CopyCommand(TextEditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.copy();
    }

}
