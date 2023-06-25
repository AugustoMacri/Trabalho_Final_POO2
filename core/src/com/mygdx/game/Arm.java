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
        shootSound = Gdx.audio.newSound(Gdx.files.internal("audio/shot.wav"));
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
                        shootSound.play(0.2f);
                        bullets.add(bullet);
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

    public void render() {
    if (arm) {
        // Obter as coordenadas X e Y do mouse
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        // Calcular o ângulo entre o braço e o mouse
        Vector2 armPosition = new Vector2(positionArmX + 12, positionArmY + 12); // Posição central do braço
        Vector2 mousePosition = new Vector2(mouseX, mouseY);
        float angle = MathUtils.radiansToDegrees * MathUtils.atan2(mousePosition.y - armPosition.y, mousePosition.x - armPosition.x);

        // Definir a rotação da imagem do braço
        batch.begin();
        batch.draw(textureArm, positionArmX, positionArmY + 15, textureArm.getWidth() / 2 - 8, textureArm.getHeight() / 2, textureArm.getWidth(), textureArm.getHeight(), 2, 2, angle, 0, 0, textureArm.getWidth(), textureArm.getHeight(), false, false);
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