package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class CollisionManager {

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

    public void checkCollision(Character character, Obstacle obstacle) {
    
    }

    //
    public void checkCollision(ZombieNormal obj1, Bullet obj2){
        if(obj1.getLIFE() > 0){
            boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

            if(isOverlapping){
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
