package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Character {
    
    private Texture character;
    private SpriteBatch batch;
    private float playerPositionX, playerPositionY;
    private int SPEED = 100; //Setting the speed of the main character
    private Rectangle rectangle;
    private ShapeRenderer shapeRenderer;

    public Character(Texture texture, float playerPositionX, float playerPositionY){
        this.character = texture;
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;

        rectangle = new Rectangle(playerPositionX, playerPositionY, 64, 64);
        shapeRenderer = new ShapeRenderer();
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

        rectangle.setPosition(playerPositionX, playerPositionY);

    }

    public void detectCollision(Cube cube){
        boolean isOverlapping = rectangle.overlaps(cube.getRectangle());

        if(isOverlapping){
            System.out.println("Collision Detected");
        }
    }


    public void render(SpriteBatch batch){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        shapeRenderer.end();
        batch.draw(character, playerPositionX, playerPositionY, 64, 64);

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

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
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
