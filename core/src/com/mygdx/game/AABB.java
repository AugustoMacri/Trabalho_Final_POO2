package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class AABB {
    public Vector2 center;
    public Vector2 halfSize; //Calculations we'll need the half size, so instead os falculating every time we'll simply memorize it

    public AABB(Vector2 center, Vector2 halfSize){
        this.center = center;
        this.halfSize = halfSize;
    }

    //Checks if two AABBs collide
    public boolean Overlaps(AABB other){
        if (Mathf.Abs(center.x - other.center.x) > halfSize.x + other.halfSize.x) return false;
        if (Mathf.Abs(center.y - other.center.y) > halfSize.y + other.halfSize.y) return false;
        return true;
    }
}
