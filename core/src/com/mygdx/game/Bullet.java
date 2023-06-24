package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

import java.util.ArrayList;

public class Bullet implements Cloneable{
    private SpriteBatch batch;
    private int SPEED = 1000; //bullet speed
    private int DANO = 65;
    private int LIFE = 100;

    private float directionX; // Direção X da bala
    private float directionY; // Direção Y da bala

    private Arm arm2;
    private static Texture textureBullet;
    private boolean attack;
    protected Rectangle rectangle;

    private float xBullet;
    private float yBullet;
    private ShapeRenderer shapeRenderer;

    public Bullet(Arm arm, Texture texture){
        arm2 = arm;
        this.xBullet = arm.getPositionArmX()+16;
        this.yBullet = arm.getPositionArmY()+16;
        textureBullet = texture;
        batch = new SpriteBatch();
        rectangle = new Rectangle(xBullet + 8, yBullet + 3, 5, 5);
        shapeRenderer = new ShapeRenderer();

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
        if (LIFE > 0 && xBullet < Gdx.graphics.getWidth()) {
            if (!attack) {
                attack = true;
                yBullet = arm2.getPositionArmY() + 16;

                // Definir a direção inicial da bala com base na posição atual do mouse
                float mouseX = Gdx.input.getX();
                float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

                directionX = mouseX - xBullet;
                directionY = mouseY - yBullet;
                float length = (float) Math.sqrt(directionX * directionX + directionY * directionY);
                directionX /= length;
                directionY /= length;
            }

            if (attack) {
                if (xBullet < Gdx.graphics.getWidth()) {
                    // Atualizar a posição da bala com base na direção e velocidade
                    xBullet += directionX * SPEED * Gdx.graphics.getDeltaTime();
                    yBullet += directionY * SPEED * Gdx.graphics.getDeltaTime();
                } else {
                    xBullet = arm2.getPositionArmX() + 16;
                    attack = false;
                    arm2.getBullets().remove(0);
                }
            } else {
                xBullet = arm2.getPositionArmX() + 16;
                yBullet = arm2.getPositionArmY() + 16;
            }
        } else {
            arm2.getBullets().remove(0);
        }
    }


    public void render (){
        if(LIFE > 0) {
            batch.begin();
            batch.draw(textureBullet, xBullet, yBullet);
            batch.end();
            
        }

    }

    public void dispose(){
        textureBullet.dispose();
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getLIFE() {
        return LIFE;
    }



    public float getxBullet() {
        return xBullet;
    }

    public void setxBullet(float xBullet) {
        this.xBullet = xBullet;
    }

    public float getyBullet() {
        return yBullet;
    }

    public void setyBullet(float yBullet) {
        this.yBullet = yBullet;
    }

    public void setLIFE(int LIFE) {
        this.LIFE = LIFE;
    }

    public int getSPEED() {
        return SPEED;
    }
    public int getDANO() {
        return DANO;
    }
    public void setDANO(int dANO) {
        DANO = dANO;
    }

    

}