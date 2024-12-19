package com.example.texteditorproject.observer;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SyntaxHighlighter implements SyntaxObserver {
    private final TextFlow textFlow;

    public SyntaxHighlighter(TextFlow textFlow) {
        this.textFlow = textFlow;
    }

    @Override
    public void update(String content) {
        textFlow.getChildren().clear();
        String[] tokens = content.split("\\s+");

        for (String token : tokens) {
            Text text = new Text(token + " ");
            if (token.matches("(if|else|for|while|return|int|void|String)")) { // Ключові слова Java
                text.setStyle("-fx-fill: blue; -fx-font-weight: bold;");
            } else if (token.matches("[0-9]+")) { // Числа
                text.setStyle("-fx-fill: red;");
            } else {
                text.setStyle("-fx-fill: black;");
            }
            textFlow.getChildren().add(text);
        }
    }
}