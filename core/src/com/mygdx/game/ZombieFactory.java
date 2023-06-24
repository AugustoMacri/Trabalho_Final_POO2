package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public interface ZombieFactory {
    Enemy createZombie(Texture enemyTexture, float x, float y);
}
