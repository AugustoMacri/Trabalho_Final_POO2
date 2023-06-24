package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
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
    private CollisionManager collisionManager;
    private ArrayList <Bullet> bullets;
    private float shootCooldown = 0f;
    private Sound shootSound;

    public Arm(Texture texture, float playerPositionX, float playerPositionY, Texture textureArm, float positionArmX, float positionArmY){
        super(texture, playerPositionX, playerPositionY);
        batch = new SpriteBatch();
        this.textureArm = textureArm;
        this.positionArmX = positionArmX;
        this.positionArmY = positionArmY;
        bullets = new ArrayList<>();
        bulletTexture = new Texture("images/bullet1.png");
        shootSound = Gdx.audio.newSound(Gdx.files.internal("audio/gun_shot.mp3"));
    }

    
    public void shoot() {
        if (arm) {
            Bullet bullet;
            for (int i = 0; i < this.getBullets().size(); i++) {
                bullet = this.getBullets().get(i);
                bullet.render();
                bullet.moveBullet();

            }
                    if (Gdx.input.isKeyPressed(Keys.SPACE) && shootCooldown <= 0f) {
                        bullet = new Bullet(this, bulletTexture);
                        shootSound.play(0.3f);
                        bullets.add(bullet.clone());
                        shootCooldown = 0.25f;
                    }
                    if (shootCooldown > 0f) {
                        shootCooldown -= Gdx.graphics.getDeltaTime();
                    }
                }
            }


    public void update(Character character){
        positionArmX = character.getPlayerPositionX();
        positionArmY = character.getPlayerPositionY();

        if(character.getLIFE() == 0 || character.getLIFE() < 0){
            arm = false;
        }

    }

    public void render(){
        if(arm){
            
            if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                textureArm = new Texture("images/gun_left.png");
            } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                textureArm = new Texture("images/gun_right.png");
            } else if (Gdx.input.isKeyPressed(Keys.UP)) {
                textureArm = new Texture("images/gun_up.png");
            } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                textureArm = new Texture("images/gun_down.png");
            }
                    
            batch.begin();
            batch.draw(textureArm, positionArmX + 6, positionArmY + 25, 14, 14);
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

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
    

}