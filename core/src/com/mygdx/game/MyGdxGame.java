package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

    Texture characterTexture, cubeTexture, enemyTexture; 
    
    Character character;
    Cube cube;
    ZombieBuff enemy;

    SpriteBatch spriteBatch;



    @Override
    public void create () {

        //creating the character
        //---------------------------------------------------------------------------------
        characterTexture = new Texture("character1.Right.png");
        character = new Character(characterTexture, 0, 0);
        spriteBatch = new SpriteBatch();


        //creating the cube
        //---------------------------------------------------------------------------------
        cubeTexture = new Texture("Cube.png"); //Still not working!!!!
        cube = new Cube(cubeTexture, 150, 150, 50, 50);
        spriteBatch = new SpriteBatch();

        //creating the enemy
        //---------------------------------------------------------------------------------
        enemyTexture = new Texture("zombie3.png");
        enemy = new ZombieBuff(enemyTexture, 200, 200, 50, 50);
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render () { 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 	//Set de screen back at black

        spriteBatch.begin();
        character.render();
        enemy.render();
        spriteBatch.end();
        cube.render();
        cube.handleInput();
        character.update();
        enemy.update(character);
        character.detectCollision(cube);
        character.detectCollision(enemy);
    }

    @Override
    public void dispose(){
        character.dispose();
        spriteBatch.dispose();
        cube.dispose();
    }
}