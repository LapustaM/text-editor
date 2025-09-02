package com.example.texteditorproject.command;

public class CommandInvoker { ;

    public void executeCommand(Command command) {
        command.execute();
    }
}