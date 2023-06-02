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

    Texture characterTexture, cubeTexture, bulletTexture;
    
    Character character;
    Bullet bullet;
    Cube cube;

    SpriteBatch spriteBatch;
    List<Bullet> bullets;



    @Override
    public void create () {

        //creating the character
        //---------------------------------------------------------------------------------
        characterTexture = new Texture("character1.Right.png");
        bulletTexture = new Texture("bullet.png");
        character = new Character(characterTexture, 0, 0);
        spriteBatch = new SpriteBatch();

        bullet = new Bullet(character, bulletTexture);
        bullets = new ArrayList<>();



        //creating the cube
        //---------------------------------------------------------------------------------
        cubeTexture = new Texture("Cube.png"); //Still not working!!!!
        cube = new Cube(cubeTexture, 150, 150, 50, 50);
        spriteBatch = new SpriteBatch();


    }

    @Override
    public void render () { 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 	//Set de screen back at black

        spriteBatch.begin();
        bullet.render();
        bullet.generateBullet();
        character.render();
        spriteBatch.end();
        cube.render();
        cube.handleInput();
        character.update();
        character.detectCollision(cube);
    }

    @Override
    public void dispose(){
        bullet.dispose();
        character.dispose();
        spriteBatch.dispose();
        cube.dispose();
    }
}