package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {

    private int SPEED = 300; //bullet speed
    private static Texture texture;

    float x, y;

    private boolean remove = false;

    public Bullet(float x, float y){
        this.x = x;
        this.y = y;

        texture = new Texture("bullet.png");
    }

    public void update(float deltaTime){
        x += SPEED * deltaTime;
        if(x > Gdx.graphics.getWidth()){
            remove = true;
        }
    }

    public void render (SpriteBatch batch){
        batch.draw(texture, x, y);
    }
}


