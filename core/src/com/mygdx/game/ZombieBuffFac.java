package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ZombieBuffFac implements ZombieFactory {

    public Enemy createZombie(Texture enemyTexture, float x, float y) {
        return new ZombieBuff(enemyTexture, x, y, 64, 64, 20, 150, 10, 100);
    }
    
}
