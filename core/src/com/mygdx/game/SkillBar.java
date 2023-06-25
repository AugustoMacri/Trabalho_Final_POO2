package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;

public class SkillBar{
    private float lenght, width, positionX, positionY;

    public SkillBar(){
        this.lenght = 10;
        this.width = 5;
    }

    public void update(Character character){
        positionX = character.getPlayerPositionX();
        positionY = character.getPlayerPositionY();
    }

    public void render(Character character){
        if(character.getLIFE() > 0){
            width = ((30 - character.getSkillCooldown()) * 3.32f);
    
            // Draw de skill bar 
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(positionX - 32, positionY + 64, width, 5);
            shapeRenderer.end();
        }
    }

}
