package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Cube {

    private Rectangle bounds;
    private ShapeRenderer shapeRenderer;
    
    float x;
    float y;
    float width;
    float height;

    private boolean dragging;
    private float dragX, dragY;

    public Cube(float x, float y, float width, float height){
        bounds = new Rectangle(x, y, width, height);
        shapeRenderer = new ShapeRenderer();
    }

    //Here is the function that can make the Cube be dragged from a side to another
    //---------------------------------------------------------------------------------------
     public void handleInput(){
         if(Gdx.input.justTouched()){               //check if there was any contact with the screen
             float touchX = Gdx.input.getX();       //get the coordenates of the touch
             float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
             
             if(bounds.contains(touchX, touchY)){   //checking if the touch is in the boundries of the cube
                 dragging = true;
                 dragX = touchX - bounds.x;         //tracks the difference between touch and current cube position
                 dragY = touchY - bounds.y;
                 
                }
            }
            
            if(Gdx.input.isTouched() && dragging){  //if dragging = true and the touch is still ocurring, we can drag the cube
                float touchX = Gdx.input.getX();
                float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
                
                bounds.x = touchX - dragX;
                bounds.y = touchY - dragY;
            }
            
            if(!Gdx.input.isTouched() && dragging){ //Check if the touch is still ocurring, if not, dragging = false
                dragging = false;   
            }
        }
    //---------------------------------------------------------------------------------------
        
    public void render(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.end();
    }

    public void dispose(){
        shapeRenderer.dispose();
    }



    
}
