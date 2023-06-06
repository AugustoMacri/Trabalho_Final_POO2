package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Bullet {

    private final int SPEED = 300; //bullet speed
    private SpriteBatch batch;
    private Character character2;
    private static Texture textureBullet;
    private boolean attack;
    private boolean wasShotted;
    private ArrayList<Bullet> bullets;
    private Bullet b;

    float xBullet;
    static float yBullet;

    public Bullet(Character character, Texture texture){
        character2 = character;
        batch = new SpriteBatch();
        this.xBullet = character.getPlayerPositionX()+16;
        this.yBullet = character.getPlayerPositionY()+16;
        textureBullet = texture;
        wasShotted = false;
        bullets = new ArrayList<>();
    }

    public void generateBullet(){

        if((Gdx.input.isKeyPressed(Input.Keys.SPACE))){
            b = new Bullet(character2, textureBullet);
            bullets.add(b);
            attack = true;
            yBullet = character2.getPlayerPositionY() + 16;
            b.moveBullet();
        }
    }

    public void moveBullet(){
        if(attack){
            //BALA VAI ATE O LIMITE DA TELA E DEPOIS VOLTA PARA A POSICAO INICIAL
            if(xBullet < Gdx.graphics.getWidth()){
                xBullet += SPEED*Gdx.graphics.getDeltaTime();
            }else{
                attack = false;
            }
        }
        this.stopBullet();
    }

    public void stopBullet(){
        if(!attack) {
            xBullet = character2.getPlayerPositionX() + 16;
            yBullet = character2.getPlayerPositionY() + 16;
        }
    }

    public void render (){
        batch.begin();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            batch.draw(textureBullet, bullet.xBullet, bullet.yBullet);
        }
        batch.end();
    }

    public void dispose(){
        textureBullet.dispose();
    }

}


