package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class ZombieFast extends Enemy{
   
    public ZombieFast(Texture texture, float x, float y, float width, float height, int SPEED, int LIFE, int DANO){
        super(texture, x, y, width, height, 65, 50, 1);
        
    }

    @Override
    public void update(Character character){
        previousPosition = new Vector2(x, y);


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

}
