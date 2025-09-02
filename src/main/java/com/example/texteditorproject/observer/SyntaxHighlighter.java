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

        String keywordsRegex = "\\b(if|else|for|while|return|int|void|String|class|public|private|protected|static|final|new|try|catch|throw|throws|extends|implements)\\b";
        String numberRegex = "\\b\\d+\\b";

        String[] lines = content.split("\n");

        for (String line : lines) {
            String[] tokens = line.split("(?<=\\s)|(?=\\s)");

            for (String token : tokens) {
                Text text = new Text(token);
                if (token.matches(keywordsRegex)) {
                    text.setStyle("-fx-fill: blue; -fx-font-weight: bold;");
                } else if (token.matches(numberRegex)) {
                    text.setStyle("-fx-fill: green;");
                } else {
                    text.setStyle("-fx-fill: black;");
                }
                textFlow.getChildren().add(text);
            }
            textFlow.getChildren().add(new Text("\n"));
        }
    }
}
