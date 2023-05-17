package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update(){
        if(x < (0 + size) || x > (Gdx.graphics.getWidth() - size)){
            xSpeed = -xSpeed;
        } 
        if(y < (0 + size) || y > (Gdx.graphics.getHeight()- size)){
            ySpeed = -ySpeed;
        }

    }
    public void draw(ShapeRenderer shape){
        shape.circle(x, y, size);
    }
    
}