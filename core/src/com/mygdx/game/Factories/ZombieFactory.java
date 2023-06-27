package com.mygdx.game.Factories;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Zombies.ScoreObserver;

public interface ZombieFactory {
    ScoreObserver createZombie(Texture enemyTexture, float x, float y);
}
