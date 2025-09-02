package com.example.texteditorproject.observer;

import java.util.ArrayList;
import java.util.List;

public class EditorSubject implements Subject{
    private final List<SyntaxObserver> observers = new ArrayList<>();

    public void addObserver(SyntaxObserver observer) {
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
