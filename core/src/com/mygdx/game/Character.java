package com.mygdx.game;

import java.util.ArrayList;

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

public class Character {
    
    private Texture character;
    private SpriteBatch batch;
    private float playerPositionX, playerPositionY;
    private int SPEED = 100; //Setting the speed of the main character
    private Rectangle rectangle;
    private ShapeRenderer shapeRenderer;
<<<<<<< Updated upstream
    
=======
    private Vector2 previousPosition;
    private int SPEED = 100; 
    private int LIFE = 6000;
    private static int score;
>>>>>>> Stashed changes

    public Character(Texture texture, float playerPositionX, float playerPositionY){
        batch = new SpriteBatch();
        this.character = texture;
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
<<<<<<< Updated upstream

        rectangle = new Rectangle(playerPositionX + 32, playerPositionY + 32, 64, 64);
        shapeRenderer = new ShapeRenderer();
=======
        score = 0;
        rectangle = new Rectangle(playerPositionX + 32, playerPositionY + 32, 25, 60);
>>>>>>> Stashed changes
    }

    public void update(){
        //Character movimentation
        //------------------------------------------------------------
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

        //Collision Rectangle position
        //------------------------------------------------------------
        rectangle.setPosition(playerPositionX, playerPositionY);


    }

    public void detectCollision(Cube cube){
        boolean isOverlapping = rectangle.overlaps(cube.getRectangle());

        if(isOverlapping){
            System.out.println("Collision Detected" + playerPositionX + " " + playerPositionY);
        }
    }


    public void render(){
        batch.begin();
        batch.draw(character, playerPositionX, playerPositionY, 64, 64);
        batch.end();

        //Red Aquare around the hitbox
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        shapeRenderer.end();
    }

    public void dispose(){
        character.dispose();
    }

    public Texture getCharacter() {
        return character;
    }

    public void setCharacter(Texture character) {
        this.character = character;
    }

    public float getPlayerPositionX() {
        return playerPositionX;
    }

    public void setPlayerPositionX(float playerPositionX) {
        this.playerPositionX = playerPositionX;
    }

    public float getPlayerPositionY() {
        return playerPositionY;
    }

    public void setPlayerPositionY(float playerPositionY) {
        this.playerPositionY = playerPositionY;
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int sPEED) {
        SPEED = sPEED;
    }
}
