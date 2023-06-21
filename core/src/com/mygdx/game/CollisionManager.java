package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.Arm.bullets;

public class CollisionManager {

    // Collision Between Character and Enemys
    //--------------------------------------------------------------------------------- 
    public void checkCollision(Character obj1, ZombieNormal obj2){
        if(obj1.getLIFE() > 0){
            boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

            if(isOverlapping){
            obj1.setLIFE(obj1.getLIFE() - obj2.getDANO());
            }
        }
    }
    
    public void checkCollision(Character obj1, ZombieFast obj2){
        if(obj1.getLIFE() > 0){
            boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

            if(isOverlapping){
            obj1.setLIFE(obj1.getLIFE() - obj2.getDANO());
            }
        }
    }

    public void checkCollision(Character obj1, ZombieBuff obj2){
        if(obj1.getLIFE() > 0){
            boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

            if(isOverlapping){
            obj1.setLIFE(obj1.getLIFE() - obj2.getDANO());
            }
        }
    }

    // Collision Between X and Obstacles
    //---------------------------------------------------------------------------------
    public void checkCollision(Character character, Obstacle obstacle) {
        boolean isOverlapping = character.getRectangle().overlaps(obstacle.getRectangle());
        if(isOverlapping){
            Vector2 previousPosition = character.getPreviousPosition();
            character.setPlayerPositionX(previousPosition.x);
            character.setPlayerPositionY(previousPosition.y);

        }
    }

    public void checkCollision(ZombieNormal enemy, Obstacle obstacle) {
        boolean isOverlapping = enemy.getRectangle().overlaps(obstacle.getRectangle());
        if(isOverlapping){
            Vector2 previousPosition = enemy.getPreviousPosition();
            enemy.setX(previousPosition.x);
            enemy.setY(previousPosition.y);

        }
    }

    public void checkCollision(ZombieFast enemy, Obstacle obstacle) {
        boolean isOverlapping = enemy.getRectangle().overlaps(obstacle.getRectangle());
        if(isOverlapping){
            Vector2 previousPosition = enemy.getPreviousPosition();
            enemy.setX(previousPosition.x);
            enemy.setY(previousPosition.y);

        }
    }

    public void checkCollision(ZombieBuff enemy, Obstacle obstacle) {
        boolean isOverlapping = enemy.getRectangle().overlaps(obstacle.getRectangle());
        if(isOverlapping){
            Vector2 previousPosition = enemy.getPreviousPosition();
            enemy.setX(previousPosition.x);
            enemy.setY(previousPosition.y);

        }
    }

    
    public void checkCollision(Enemy obj1, Bullet obj2){
        if(obj1.getLIFE() > 0) {
            boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());
            if (isOverlapping) {
                obj1.setLIFE(obj1.getLIFE() - obj2.getDANO());
            }
        }
    }

    public void checkCollision(Character obj1, Cube obj2){
        boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

        if(isOverlapping){
            //Will make the character stop
        }
    }


}
