package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

import static com.mygdx.game.MyGdxGame.enemies;

public abstract class Enemy {
    protected Texture texture;
    protected float x, y, width, height;
    protected SpriteBatch batch;
    protected Rectangle rectangle;
    protected Vector2 previousPosition;
    protected int SPEED;
    protected int LIFE;
    protected int DANO;
    protected long  lastEnemyTime;

    public Enemy(Texture texture, float x, float y, float width, float height){
        batch = new SpriteBatch();
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(x, y, 25, 50);
    }

    public abstract void update(Character character);

    public void render(){
        for(Enemy enemy : enemies) {
            batch.begin();
            batch.draw(texture, enemy.x, enemy.y);
            batch.end();
        }
    }

    public abstract void spawnEnemies();
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

    public Vector2 getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(Vector2 previousPosition) {
        this.previousPosition = previousPosition;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int sPEED) {
        SPEED = sPEED;
    }

    public int getLIFE() {
        return LIFE;
    }

    public void setLIFE(int lIFE) {
        LIFE = lIFE;
    }

    public int getDANO() {
        return DANO;
    }

    public void setDANO(int dANO) {
        DANO = dANO;
    }

    
    
}
