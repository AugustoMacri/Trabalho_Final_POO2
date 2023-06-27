package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Player.Bullet;
import com.mygdx.game.Player.Character;
import com.mygdx.game.World.Cube;
import com.mygdx.game.World.Obstacle;
import com.mygdx.game.Zombies.Enemy;

import jdk.internal.icu.lang.UCharacter;

public class CollisionManager {

    // Collision Between Character and Enemies
    //--------------------------------------------------------------------------------- 
    public void checkCollision(Character obj1, Enemy obj2){
        if(obj1.getLIFE() > 0){
            boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

            // if a rectangle overlaps another, character loses his life
            if(isOverlapping){
                obj1.setLIFE(obj1.getLIFE() - obj2.getDANO());
            }
        }
    }


    // Collision Between X and Obstacles
    //---------------------------------------------------------------------------------
    public void checkCollision(Character character, Obstacle obstacle) {
        boolean isOverlapping = character.getRectangle().overlaps(obstacle.getRectangle());

        // if a rectangle overlaps another, character is pushed to his previous position
        if(isOverlapping){
            Vector2 previousPosition = character.getPreviousPosition();
            character.setPlayerPositionX(previousPosition.x - (character.getPlayerPositionX() - previousPosition.x));
            character.setPlayerPositionY(previousPosition.y - (character.getPlayerPositionY() - previousPosition.y));

        }
    }

    public void checkCollision(Enemy enemy, Obstacle obstacle) {
        boolean isOverlapping = enemy.getRectangle().overlaps(obstacle.getRectangle());

        // if a rectangle overlaps another, enemy is pushed to his previous position
        if(isOverlapping){
            Vector2 previousPosition = enemy.getPreviousPosition();
            enemy.setX(previousPosition.x - (enemy.getX() - previousPosition.x));
            enemy.setY(previousPosition.y - (enemy.getX() - previousPosition.x));

        }
    }



    // Collision Between X and cube
    //---------------------------------------------------------------------------------
    public void checkCollision(Character character, Cube cube){
        boolean isOverlapping = character.getRectangle().overlaps(cube.getRectangle());

        // if a rectangle overlaps another, character is pushed to his previous position
        if(isOverlapping){
            Vector2 previousPosition = character.getPreviousPosition();
            character.setPlayerPositionX(previousPosition.x - (character.getPlayerPositionX() - previousPosition.x));
            character.setPlayerPositionY(previousPosition.y - (character.getPlayerPositionY() - previousPosition.y));
        }
    }


    public void checkCollision(Enemy character, Cube cube){
        boolean isOverlapping = character.getRectangle().overlaps(cube.getRectangle());

        // if a rectangle overlaps another, enemy is pushed to his previous position
        if(isOverlapping){
            Vector2 previousPosition = character.getPreviousPosition();
            character.setX(previousPosition.x - (character.getX() - previousPosition.x));
            character.setY(previousPosition.y - (character.getY() - previousPosition.y));

        }
    }

    public void checkCollision(Enemy enemy, Bullet bullet) {

        // if a collision occurs, the lives of both objects are updated based on the damage inflicted (maybe killing each other)
        if(enemy.getLIFE() > 0){
            if(enemy.getX() + enemy.getWidth() > bullet.getxBullet() && enemy.getX() < bullet.getxBullet() + bullet.getRectangle().getWidth()
                    && enemy.getY() + enemy.getHeight() > bullet.getyBullet() && enemy.getY() < bullet.getyBullet() + bullet.getRectangle().getHeight()) {
                if(enemy.getDAMAGEBULLET() >= bullet.getLIFE()){
                    bullet.setLIFE(0);
                }
                if(bullet.getDANO() >= enemy.getLIFE()) {
                    enemy.setLIFE(0);
                }else{
                    bullet.setLIFE(bullet.getLIFE() - enemy.getDAMAGEBULLET());
                    enemy.setLIFE(enemy.getLIFE() - bullet.getDANO());
                }
            }
        }
    }
}
