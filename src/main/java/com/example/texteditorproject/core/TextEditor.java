package com.example.texteditorproject.core;

import com.example.texteditorproject.command.Command;
import com.example.texteditorproject.model.Document;
import com.example.texteditorproject.strategy.EncodingStrategy;

import java.util.List;

public class TextEditor {
    private Document document;
    private EncodingStrategy encodingStrategy;
    private List<Command> commands;

    public void setEncodingStrategy(EncodingStrategy strategy) {
        this.encodingStrategy = strategy;
    }

    public void executeCommand(Command command) {
        command.execute();
        commands.add(command);
    }

    public void undoCommand() {
        if (!commands.isEmpty()) {
            Command command = commands.remove(commands.size() - 1);
//            command.undo();
        }
    }
}
