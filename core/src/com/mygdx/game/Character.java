package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Character {
    
    private Texture character;
    private SpriteBatch batch;
    private float playerPositionX, playerPositionY;
    private int SPEED = 100; //Setting the speed of the main character

    public Character(Texture texture, float playerPositionX, float playerPositionY){
        this.character = texture;
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
    }

    public void update(){
        if(Gdx.input.isKeyPressed(Keys.W)) {
            playerPositionY += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            playerPositionY -= SPEED * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyPressed(Keys.D)) {
            playerPositionX += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            playerPositionX -= SPEED * Gdx.graphics.getDeltaTime();
        }

        if(playerPositionX < 0){
            playerPositionX = 0;
        } else if (playerPositionX > Gdx.graphics.getWidth() - 64){
            playerPositionX = Gdx.graphics.getWidth() - 64;
        }
        if(playerPositionY < 0) {
            playerPositionY = 0;
        } else if (playerPositionY > Gdx.graphics.getHeight() - 64) {
            playerPositionY = Gdx.graphics.getHeight() - 64;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(character, playerPositionX, playerPositionY, 64, 64);
    }

    public void dispose(){
        character.dispose();
    }
    
}
