package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;


public class MyGdxGame extends ApplicationAdapter {

    private Music backGroundMusic;
    Texture backGroundTexture, characterTexture, armTexture, cubeTexture, enemyTextureNormal, enemyTextureFast, enemyTextureBuff, enemyTexture;
    BackGround backGround;
    Obstacle mall, carV, carB, carO, carO2, carO3, p1, p2, p3, p4, garbage, barrier;
    Character character;
    Arm arm;
    HealthBar healthBar;
    Cube cube;
    ZombieNormal enemyNormal;
    ZombieFast enemyFast;
    ZombieBuff enemyBuff;
    CollisionManager collisionManager = new CollisionManager();
    SpriteBatch spriteBatch;



    @Override
    public void create () {
        spriteBatch = new SpriteBatch();

        //Background Music
        //---------------------------------------------------------------------------------
        backGroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/battle.wav"));
        backGroundMusic.setLooping(true);
        //backGroundMusic.play();

        //creating the backGround
        //---------------------------------------------------------------------------------
        backGroundTexture = new Texture("images/Fundo_Fase1.png");
        backGround = new BackGround(backGroundTexture);

        //creating the character
        //---------------------------------------------------------------------------------
        characterTexture = new Texture("images/character1.Right.png");
        character = new Character(characterTexture, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        healthBar = new HealthBar();
        armTexture = new Texture("images/gun.png");
        arm = new Arm(characterTexture, 0, 0, armTexture, 0, 0);

        //creating the cube
        //---------------------------------------------------------------------------------
        cubeTexture = new Texture("images/Cube.png");
        cube = new Cube(cubeTexture, 550, 570, 50, 50);
        
        //creating Enemys
        //---------------------------------------------------------------------------------
        enemyTextureNormal = new Texture("images/zombie.png");
        enemyNormal = new ZombieNormal(enemyTextureNormal, 200, 200, 50, 50);
        enemyTextureFast = new Texture("images/zombie2.png");
        enemyFast = new ZombieFast(enemyTextureFast, 200, 200, 50, 50);
        enemyTextureBuff = new Texture("images/zombie3.png");
        enemyBuff = new ZombieBuff(enemyTextureBuff, 200, 200, 50, 50);

        //Creating Obstacle
        //---------------------------------------------------------------------------------
        mall = new Obstacle(263, 600, 770, 120);
        carV = new Obstacle(397, 359, 90, 50);
        carB = new Obstacle(799, 147, 90, 50);
        carO = new Obstacle(237, 98, 30, 40);
        carO2 = new Obstacle(267, 120, 30, 40);
        carO3 = new Obstacle(289, 136, 30, 40);
        p1 = new Obstacle(366, 428, 14, 14);
        p2 = new Obstacle(905, 428, 14, 14);
        p3 = new Obstacle(366, 11, 14, 14);
        p4 = new Obstacle(905, 11, 14, 14);
        garbage = new Obstacle(1035, 645, 26, 70);
        barrier = new Obstacle(614, 574, 75, 20);

    }

    @Override
    public void render () { 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 	//Set de screen back at black

        //Render
        //---------------------------------------------------------------------------------
        spriteBatch.begin();
        backGround.render();
        character.render();
        arm.shoot();
        healthBar.render(character);
        arm.render();
        enemyNormal.render();
        enemyFast.render();
        enemyBuff.render();
        spriteBatch.end();
        cube.render();
        cube.handleInput();
        

        //Updates
        //---------------------------------------------------------------------------------
        character.update();
        healthBar.update(character);
        arm.update(character);
        enemyNormal.update(character);
        enemyFast.update(character);
        enemyBuff.update(character);

        //Collision
        //---------------------------------------------------------------------------------
        collisionManager.checkCollision(character, enemyNormal);
        collisionManager.checkCollision(character, enemyFast);
        collisionManager.checkCollision(character, enemyBuff);
        collisionManager.checkCollision(character, cube);
        collisionManager.checkCollision(character, mall);
        collisionManager.checkCollision(character, carV);
        collisionManager.checkCollision(character, carB);
        collisionManager.checkCollision(character, carO);
        collisionManager.checkCollision(character, carO2);
        collisionManager.checkCollision(character, carO3);
        collisionManager.checkCollision(character, p1);
        collisionManager.checkCollision(character, p2);
        collisionManager.checkCollision(character, p3);
        collisionManager.checkCollision(character, p4);
        collisionManager.checkCollision(character, garbage);
        collisionManager.checkCollision(character, barrier);
        collisionManager.checkCollision(enemyNormal, mall);
        collisionManager.checkCollision(enemyNormal, carV);
        collisionManager.checkCollision(enemyNormal, carB);
        collisionManager.checkCollision(enemyNormal, carO);
        collisionManager.checkCollision(enemyNormal, carO2);
        collisionManager.checkCollision(enemyNormal, carO3);
        collisionManager.checkCollision(enemyNormal, p1);
        collisionManager.checkCollision(enemyNormal, p2);
        collisionManager.checkCollision(enemyNormal, p3);
        collisionManager.checkCollision(enemyNormal, p4);
        collisionManager.checkCollision(enemyNormal, garbage);
        collisionManager.checkCollision(enemyNormal, barrier);
        collisionManager.checkCollision(enemyFast, mall);
        collisionManager.checkCollision(enemyFast, carV);
        collisionManager.checkCollision(enemyFast, carB);
        collisionManager.checkCollision(enemyFast, carO);
        collisionManager.checkCollision(enemyFast, carO2);
        collisionManager.checkCollision(enemyFast, carO3);
        collisionManager.checkCollision(enemyFast, p1);
        collisionManager.checkCollision(enemyFast, p2);
        collisionManager.checkCollision(enemyFast, p3);
        collisionManager.checkCollision(enemyFast, p4);
        collisionManager.checkCollision(enemyFast, garbage);
        collisionManager.checkCollision(enemyFast, barrier);
        collisionManager.checkCollision(enemyBuff, mall);
        collisionManager.checkCollision(enemyBuff, carV);
        collisionManager.checkCollision(enemyBuff, carB);
        collisionManager.checkCollision(enemyBuff, carO);
        collisionManager.checkCollision(enemyBuff, carO2);
        collisionManager.checkCollision(enemyBuff, carO3);
        collisionManager.checkCollision(enemyBuff, p1);
        collisionManager.checkCollision(enemyBuff, p2);
        collisionManager.checkCollision(enemyBuff, p3);
        collisionManager.checkCollision(enemyBuff, p4);
        collisionManager.checkCollision(enemyBuff, garbage);
        collisionManager.checkCollision(enemyBuff, barrier);
        collisionManager.checkCollision(enemyNormal, cube);
        collisionManager.checkCollision(enemyFast, cube);
        collisionManager.checkCollision(enemyBuff, cube);
    }

    @Override
    public void dispose(){
        character.dispose();
        arm.dispose();
        spriteBatch.dispose();
        cube.dispose();
        backGroundMusic.dispose();
    }
}