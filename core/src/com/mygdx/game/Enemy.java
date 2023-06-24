package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import java.lang.Math;
import java.util.List;

public abstract class Enemy implements ScoreObserver{
    protected Texture texture;
    protected float x, y, width, height;
    protected SpriteBatch batch;
    protected Rectangle rectangle;
    protected Vector2 previousPosition;
    protected int SPEED;
    protected int LIFE;
    protected int DANO;
    protected int DAMAGEBULLET;

    public Enemy(Texture texture, float x, float y, float width, float height, int SPEED, int LIFE, int DANO, int DAMAGEBULLET){
        batch = new SpriteBatch();
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.SPEED = SPEED;
        this.LIFE = LIFE;
        this.DANO = DANO;
        this.DAMAGEBULLET = DAMAGEBULLET;

        rectangle = new Rectangle(x, y, 25, 50);
    }

    public void update(Character character){
        if(LIFE > 0) {
            previousPosition = new Vector2(x, y);

            //Zombie movimentation
            //------------------------------------------------------------
            if (x != character.getPlayerPositionX() || y != character.getPlayerPositionY()) {
                float deltaX = character.getPlayerPositionX() - x;
                float deltaY = character.getPlayerPositionY() - y;
                float angle = (float) Math.atan2(deltaY, deltaX);   //Calculate the angle between the character and the zombie

                //Calculating the new x and y coordinate
                float newX = (float) (x + SPEED * Math.cos(angle) * Gdx.graphics.getDeltaTime()); //Math.cos(angle) provides the x of the enemy movimentation thru the angle
                float newY = (float) (y + SPEED * Math.sin(angle) * Gdx.graphics.getDeltaTime()); //Math.sin(angle) provides the y of the enemy movimentation thru the angle

                x = newX;
                y = newY;

            }

            //Collision Rectangle position
            //------------------------------------------------------------
            rectangle.setPosition(x, y);
        }
    }

    

    public void render(){
        if(LIFE > 0) {
            batch.begin();
            batch.draw(texture, x - 20, y, 64, 64);
            batch.end();
        }
    }

    @Override
    public void updateScore(int SCORE){
        if(SCORE == 500){
            SPEED += 5;
            LIFE += 10;
        }else if (SCORE == 250){
            SPEED += 5;
            LIFE += 10;
        }else if (SCORE == 100){
            SPEED += 5;
            LIFE += 10;
        }
    }

    //TENTAR COLOCAR COM O OU ||

    public int getDAMAGEBULLET() {
        return DAMAGEBULLET;
    }

    public void setDAMAGEBULLET(int DAMAGEBULLET) {
        this.DAMAGEBULLET = DAMAGEBULLET;
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
