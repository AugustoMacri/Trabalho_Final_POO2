package com.mygdx.game.Zombies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class ZombieFast extends Enemy{
    public ZombieFast(Texture texture, float x, float y, float width, float height, int SPEED, int LIFE, int DANO, int DAMAGEBULLET) {
        super(texture, x, y, width, height, SPEED, LIFE, DANO, DAMAGEBULLET);
    }

}
