package com.mygdx.game;


public interface ScoreObservable {
    void addObserver(ScoreObserver enemy);
    void removeObserver(ScoreObserver enemy);
    void notifyObservers();
}
