package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ZombieNormalFac implements ZombieFactory {

    public Enemy createZombie(Texture enemyTexture, float x, float y) {
        return new ZombieNormal(enemyTexture, x, y, 64, 64, 20, 100, 5, 70);
    }
    
}
