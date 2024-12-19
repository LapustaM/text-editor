package com.example.texteditorproject.observer;

import java.util.ArrayList;
import java.util.List;

public class EditorSubject {
    private final List<SyntaxObserver> observers = new ArrayList<>();

    public void registerObserver(SyntaxObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(SyntaxObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String content) {
        for (SyntaxObserver observer : observers) {
            observer.update(content);
        }
    }
}
