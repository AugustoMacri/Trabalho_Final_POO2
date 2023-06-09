package com.mygdx.game;

public class CollisionManager {

    public void checkCollision(Character obj1, ZombieNormal obj2){
        boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

        if(isOverlapping){
            obj1.setLIFE(obj1.getLIFE() - 5);
            System.out.println(obj1.getLIFE());
        }
    }
    
    public void checkCollision(Character obj1, ZombieFast obj2){
        boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

        if(isOverlapping){
            obj1.setLIFE(obj1.getLIFE() - 1);
            System.out.println(obj1.getLIFE());
        }
    }

    public void checkCollision(Character obj1, ZombieBuff obj2){
        boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

        if(isOverlapping){
            obj1.setLIFE(obj1.getLIFE() - 10);
            System.out.println(obj1.getLIFE());
        }
    }

    public void checkCollision(Character obj1, Cube obj2){
        boolean isOverlapping = obj1.getRectangle().overlaps(obj2.getRectangle());

        if(isOverlapping){
            //Will make the character stop
        }
    }

}
