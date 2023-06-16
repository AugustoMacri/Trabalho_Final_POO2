package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Bullet implements Cloneable{
    private SpriteBatch batch;

    private final int SPEED = 300; //bullet speed

    private Arm arm2;
    private static Texture textureBullet;
    private boolean attack;

    float xBullet;
    float yBullet;

    public Bullet(Arm arm, Texture texture){
        arm2 = arm;
        this.xBullet = arm.getPositionArmX()+16;
        this.yBullet = arm.getPositionArmY()+16;
        textureBullet = texture;
        batch = new SpriteBatch();
    }
    //prototype
    @Override
    public Bullet clone() {
        try {
            //é feita uma chamada ao método clone() da classe
            // Object (superclasse), que realiza uma cópia superficial
            // dos campos do objeto. Em seguida, é necessário fazer
            // um cast (conversão de tipo) para o tipo Bullet para retornar
            // a cópia corretamente tipada.
            return (Bullet) super.clone();
        } catch (CloneNotSupportedException e) {
            // Lidar com exceção de clonagem, se necessário
            return null;
        }
    }

    public void moveBullet() {
        if (!attack){
            attack = true;
            yBullet = arm2.getPositionArmY() + 16;
        }
            if (attack) {
                if (xBullet < Gdx.graphics.getWidth()) {
                    xBullet += 20;
                } else {
                    xBullet = arm2.getPositionArmX() + 16;
                    attack = false;
                    Arm.getBullets().remove(0);
                }
            } else {
                xBullet = arm2.getPositionArmX() + 16;
                yBullet = arm2.getPositionArmY() + 16;
            }
    }


    public void render (){
        batch.begin();
        batch.draw(textureBullet, xBullet, yBullet);
        batch.end();
    }

    public void dispose(){
        textureBullet.dispose();
    }

}