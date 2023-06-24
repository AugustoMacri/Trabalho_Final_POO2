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

public class HealthBar{
    private float lenght, width, positionX, positionY;

    public HealthBar(){
        this.lenght = 10;
        this.width = 1;
    }

    public void update(Character character){
        positionX = character.getPlayerPositionX();
        positionY = character.getPlayerPositionY();
    }

    public void render(Character character){
        width = (lenght * character.getLIFE()) / 600;

        // Draw de health bar 
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(positionX - 32, positionY + 54, width, 1);
        shapeRenderer.end();
    }

}
