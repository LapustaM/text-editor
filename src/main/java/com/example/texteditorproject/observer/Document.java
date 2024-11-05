package com.example.texteditorproject.observer;

import java.util.List;

public class Document {
    private List<DocumentObserver> observers;

    public void addObserver(DocumentObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(DocumentObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (DocumentObserver observer : observers) {
            observer.update();
        }
    }
}
