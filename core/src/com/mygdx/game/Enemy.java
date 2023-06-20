package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import java.lang.Math;

public abstract class Enemy {
    protected Texture texture;
    protected float x, y, width, height;
    protected SpriteBatch batch;
    protected Rectangle rectangle;

    public Enemy(Texture texture, float x, float y, float width, float height){
        batch = new SpriteBatch();
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rectangle = new Rectangle(x, y, 64, 64);
    }

    public abstract void update(Character character);

    public void render(){
        batch.begin();
        batch.draw(texture, x, y, 64, 64);
        batch.end();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    
}