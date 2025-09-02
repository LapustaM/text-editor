package com.example.texteditorproject.observer;

public interface Subject {
    void addObserver(SyntaxObserver observer);

    void removeObserver(SyntaxObserver observer);

    public void notifyObservers(String content);
}
