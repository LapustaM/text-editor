package com.example.texteditorproject.observer;

import com.example.texteditorproject.observer.SyntaxObserver;
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

        // Розширений список ключових слів Java
        String keywordsRegex = "\\b(if|else|for|while|return|int|void|String|class|public|private|protected|static|final|new|try|catch|throw|throws|extends|implements)\\b";
        String numberRegex = "\\b\\d+\\b";

        // Поділ тексту з урахуванням рядків та пробілів
        String[] lines = content.split("\n");

        for (String line : lines) {
            String[] tokens = line.split("(?<=\\s)|(?=\\s)");

            for (String token : tokens) {
                Text text = new Text(token);
                if (token.matches(keywordsRegex)) { // Ключові слова
                    text.setStyle("-fx-fill: blue; -fx-font-weight: bold;");
                } else if (token.matches(numberRegex)) { // Числа
                    text.setStyle("-fx-fill: green;");
                } else {
                    text.setStyle("-fx-fill: black;");
                }
                textFlow.getChildren().add(text);
            }
            textFlow.getChildren().add(new Text("\n")); // Додаємо новий рядок
        }
    }
}
