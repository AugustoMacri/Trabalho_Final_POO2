package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import static com.mygdx.game.MyGdxGame.enemies;


public class ZombieFast extends Enemy{
   
    public ZombieFast(Texture texture, float x, float y, float width, float height){
        super(texture, x, y, width, height);
        LIFE = 65;
        SPEED = 50;
        DANO = 1;
    }

    @Override
    public void spawnEnemies() {
            ZombieFast enemy = new ZombieFast(texture, MathUtils.random
                    (0, Gdx.graphics.getWidth() - texture.getWidth()), MathUtils.random
                    (0, Gdx.graphics.getHeight() - texture.getHeight()), texture.getWidth(), texture.getHeight());
            lastEnemyTime = TimeUtils.nanoTime();
            enemies.add(enemy);
    }

    @Override
    public void update(Character character){
        if(TimeUtils.nanoTime() - lastEnemyTime > 1000000000){
            this.spawnEnemies();
        }
        for(Iterator<Enemy> iter = enemies.iterator(); iter.hasNext();) {
            Enemy enemy = iter.next();
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

}
