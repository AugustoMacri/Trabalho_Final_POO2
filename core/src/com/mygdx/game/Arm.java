package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Arm extends Character{

    private Texture textureArm;
    private float positionArmX;
    private float positionArmY;
    private SpriteBatch batch;
    private boolean arm = true;

    
    public Arm(Texture texture, float playerPositionX, float playerPositionY, Texture textureArm, float positionArmX, float positionArmY){
        super(texture, playerPositionX, playerPositionY);
        batch = new SpriteBatch();
        this.textureArm = textureArm;
        this.positionArmX = positionArmX;
        this.positionArmY = positionArmY;


    }

    public void update(Character character){
        positionArmX = character.getPlayerPositionX();
        positionArmY = character.getPlayerPositionY();

        if(character.getLIFE() < 0){
            arm = false;
        }

    }

    public void render(){
        if(arm == true){
            batch.begin();
            batch.draw(textureArm, positionArmX - 10, positionArmY, 64, 64);
            batch.end();
        }
    }

    public Texture getTextureArm() {
        return textureArm;
    }

    public void setTextureArm(Texture textureArm) {
        this.textureArm = textureArm;
    }

    public float getPositionArmX() {
        return positionArmX;
    }

    public void setPositionArmX(float positionArmX) {
        this.positionArmX = positionArmX;
    }

    public float getPositionArmY() {
        return positionArmY;
    }

    public void setPositionArmY(float positionArmY) {
        this.positionArmY = positionArmY;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }


    

}
