package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

    public static final int SPEED = 3; //Setting the speed of the main character

    ShapeRenderer shape;	//Used to draw simple shapes
	Ball ball;


    @Override
    public void create () {
        //creating the ball
        shape = new ShapeRenderer();
        ball = new Ball(10, 10, 10, 5, 5);
    }

    @Override
    public void render () {	//Runs every frame 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 	//Set de screen back at black
        
        //----------------------------------------------------------------------------------------------------
        //draw the ball
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.update();
        ball.draw(shape);
        shape.end();
        
        //ball movement
        if (Gdx.input.isKeyPressed(Keys.UP)){
            ball.y += SPEED;
        }else if(Gdx.input.isKeyPressed(Keys.DOWN)){
            ball.y -= SPEED;
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            ball.x += SPEED;
        }else if(Gdx.input.isKeyPressed(Keys.LEFT)){
            ball.x -= SPEED;
        }
        //----------------------------------------------------------------------------------------------------


    }

    @Override
    public void dispose(){
        shape.dispose();
    }
}