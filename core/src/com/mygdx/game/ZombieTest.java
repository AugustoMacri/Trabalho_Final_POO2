package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ZombieTest extends Enemy{
    private static int SPEED = 1;
    private int LIFE = 100; 
    private int DANO = 1;
    
    public ZombieTest(Texture texture, float x, float y, float width, float height){
        super(texture, x, y, width, height);
    }
    

    @Override
    public void update(Character character) {
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


    public static int getSPEED() {
        return SPEED;
    }


    public static void setSPEED(int sPEED) {
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
