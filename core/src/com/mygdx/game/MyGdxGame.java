package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
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

        //creating the character
        //---------------------------------------------------------------------------------
        characterTexture = new Texture("character1.Right.png");
        character = new Character(characterTexture, 0, 0);
        healthBar = new HealthBar();
        armTexture = new Texture("Arm1.png");
        arm = new Arm(characterTexture, 0, 0, armTexture, 0, 0);

        //creating the backGround
        //---------------------------------------------------------------------------------
        backGroundTexture = new Texture("Fundo_Fase1.png");
        backGround = new BackGround(backGroundTexture);

        //creating the cube
        //---------------------------------------------------------------------------------
        cubeTexture = new Texture("Cube.png"); //Still not working!!!!
        cube = new Cube(cubeTexture, 150, 150, 50, 50);
        
        //creating Enemys
        //---------------------------------------------------------------------------------
        enemyTextureNormal = new Texture("zombie.png");
        enemyNormal = new ZombieNormal(enemyTextureNormal, 200, 200, 50, 50);
        enemyTextureFast = new Texture("zombie2.png");
        enemyFast = new ZombieFast(enemyTextureFast, 200, 200, 50, 50);
        enemyTextureBuff = new Texture("zombie3.png");
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
        //obstacles
        mall.render();
        carV.render();
        carB.render();
        carO.render();
        carO2.render();
        carO3.render();
        p1.render();
        p2.render();
        p3.render();
        p4.render();
        garbage.render();
        barrier.render();

        //Updates
        //---------------------------------------------------------------------------------
        character.update();
        healthBar.update(character);
        arm.update(character);
        //enemyNormal.update(character);
        //enemyFast.update(character);
        //enemyBuff.update(character);

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
    }

    @Override
    public void dispose(){
        character.dispose();
        arm.dispose();
        spriteBatch.dispose();
        cube.dispose();
    }
}