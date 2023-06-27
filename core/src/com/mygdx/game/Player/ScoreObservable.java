package com.mygdx.game.Player;

import com.mygdx.game.Zombies.ScoreObserver;

public interface ScoreObservable {
    void addObserver(ScoreObserver enemy);
    void removeObserver(ScoreObserver enemy);
    void notifyObservers();
}
