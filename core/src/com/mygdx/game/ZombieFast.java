package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import java.lang.Math;


public class ZombieFast {

    private Texture texture;
    private float x, y, width, height;
    private SpriteBatch batch;
    private Rectangle rectangle;
    private static int SPEED = 50;
    private int LIFE = 65;


    public ZombieFast(Texture texture, float x, float y, float width, float height){
        batch = new SpriteBatch();
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rectangle = new Rectangle(x, y, 64, 64);
    }

    public void update(Character character){

        //Zombie movimentation
        //------------------------------------------------------------
        if(x != character.getPlayerPositionX() || y != character.getPlayerPositionY()){
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
