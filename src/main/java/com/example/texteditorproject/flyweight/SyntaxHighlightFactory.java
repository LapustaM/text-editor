package com.example.texteditorproject.flyweight;

import java.util.Map;
import java.util.HashMap;

public class SyntaxHighlightFactory {
    private static Map<String, SyntaxHighlightStyle> styles = new HashMap<>();

    public static SyntaxHighlightStyle getStyle(String tokenType) {
        if (!styles.containsKey(tokenType)) {
            // Create a new style if it doesn’t exist
            styles.put(tokenType, new SyntaxHighlightStyle("color", "fontStyle"));
        }
        return styles.get(tokenType);
    }
}
