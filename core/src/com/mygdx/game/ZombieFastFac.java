package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ZombieFastFac implements ZombieFactory {

    public Enemy createZombie(Texture enemyTexture, float x, float y) {
        return new ZombieFast(enemyTexture, x, y, 64, 64, 50, 65, 1, 40);
    }
    
}
