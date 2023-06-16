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

    Texture characterTexture, armTexture, cubeTexture, enemyTextureNormal, enemyTextureFast, enemyTextureBuff, enemyTexture; 
    
    Character character;
    Arm arm;
    HealthBar healthBar;
    Cube cube;
    ZombieNormal enemyNormal;
    ZombieFast enemyFast;
    ZombieBuff enemyBuff;
    CollisionManager collisionManager = new CollisionManager();
    ZombieTest enemy;
    SpriteBatch spriteBatch;



    @Override
    public void create () {

        //creating the character
        //---------------------------------------------------------------------------------
        characterTexture = new Texture("character1.Right.png");
        character = new Character(characterTexture, 0, 0);
        healthBar = new HealthBar();
        spriteBatch = new SpriteBatch();

        //creating the arm
        //---------------------------------------------------------------------------------        
        armTexture = new Texture("Arm1.png");
        arm = new Arm(characterTexture, 0, 0, armTexture, 0, 0);

        //creating the cube
        //---------------------------------------------------------------------------------
        cubeTexture = new Texture("Cube.png"); //Still not working!!!!
        cube = new Cube(cubeTexture, 150, 150, 50, 50);
        
        //creating the Normal enemy
        //---------------------------------------------------------------------------------
        enemyTextureNormal = new Texture("zombie.png");
        enemyNormal = new ZombieNormal(enemyTextureNormal, 200, 200, 50, 50);

        //creating the Fast enemy
        //---------------------------------------------------------------------------------
        enemyTextureFast = new Texture("zombie2.png");
        enemyFast = new ZombieFast(enemyTextureFast, 200, 200, 50, 50);

        //creating the Buff enemy
        //---------------------------------------------------------------------------------
        enemyTextureBuff = new Texture("zombie3.png");
        enemyBuff = new ZombieBuff(enemyTextureBuff, 200, 200, 50, 50);

        //creating the enemy
        //---------------------------------------------------------------------------------
        enemyTexture = new Texture("zombie3.png");
        enemy = new ZombieTest(enemyTexture, 200, 200, 50, 50);
    }

    @Override
    public void render () { 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 	//Set de screen back at black

        //Render
        //---------------------------------------------------------------------------------
        spriteBatch.begin();
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
        enemy.render();

        //Updates
        //---------------------------------------------------------------------------------
        character.update();
        healthBar.update(character);
        arm.update(character);
        enemyNormal.update(character);
        enemyFast.update(character);
        enemyBuff.update(character);
        enemy.update(character);

        //Collision
        //---------------------------------------------------------------------------------
        collisionManager.checkCollision(character, enemyNormal);
        collisionManager.checkCollision(character, enemyFast);
        collisionManager.checkCollision(character, enemyBuff);
        collisionManager.checkCollision(character, cube);
        collisionManager.checkCollision(character, enemy);
    }

    @Override
    public void dispose(){
        character.dispose();
        arm.dispose();
        spriteBatch.dispose();
        cube.dispose();
    }
}