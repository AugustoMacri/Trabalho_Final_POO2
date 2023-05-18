package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {


    Texture characterTexture, cubeTexture; 
    SpriteBatch spriteBatch;
    Character character;
    Cube cube;


    @Override
    public void create () {

        //creating the character
        //---------------------------------------------------------------------------------
        characterTexture = new Texture("character1.Right.png");
        character = new Character(characterTexture, 0, 0);
        spriteBatch = new SpriteBatch();
        //---------------------------------------------------------------------------------

        //creating the cube
        //---------------------------------------------------------------------------------
        cube = new Cube(100, 100, 50, 50);
        characterTexture = new Texture("Cube.png"); //Still not working!!!!
    }

    @Override
    public void render () { 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 	//Set de screen back at black
        System.out.println(("" + Gdx.graphics.getDeltaTime()));
        
        //---------------------------------------------------------------------------------
        spriteBatch.begin();
        character.render(spriteBatch);
        spriteBatch.end();
        cube.render();
        cube.handleInput();
        cube.render();
        character.update();
        //---------------------------------------------------------------------------------
    }

    @Override
    public void dispose(){
        character.dispose();
        spriteBatch.dispose();
        cube.dispose();
    }
}