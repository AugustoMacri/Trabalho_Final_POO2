package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Arm extends Character{

    private Texture textureArm, bulletTexture;
    private float positionArmX;
    private float positionArmY;
    private SpriteBatch batch;
    private boolean arm = true;
    private static ArrayList <Bullet> bullets;
    private float shootCooldown = 0f;

    
    public Arm(Texture texture, float playerPositionX, float playerPositionY, Texture textureArm, float positionArmX, float positionArmY){
        super(texture, playerPositionX, playerPositionY);
        batch = new SpriteBatch();
        this.textureArm = textureArm;
        this.positionArmX = positionArmX;
        this.positionArmY = positionArmY;
        bullets = new ArrayList<>();
        bulletTexture = new Texture("bullet.png");


    }

    public void shoot(){
        if(arm == true){
            for (int i = 0; i < this.getBullets().size(); i++) {
                    Bullet bullet = this.getBullets().get(i);
                    bullet.render();
                    bullet.moveBullet();
            }

            if(Gdx.input.isKeyPressed(Keys.SPACE) && shootCooldown <= 0f){
                Bullet bullet = new Bullet(this, bulletTexture);
                bullets.add(bullet.clone());
                shootCooldown = 0.25f;
            }
            if(shootCooldown > 0f){
                shootCooldown -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    public void update(Character character){
        positionArmX = character.getPlayerPositionX();
        positionArmY = character.getPlayerPositionY();

        if(character.getLIFE() == 0){
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

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
    

}