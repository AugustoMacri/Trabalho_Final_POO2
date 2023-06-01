package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;



public class Cube {

    private Rectangle bounds;
    private Rectangle rectangle;
    private ShapeRenderer shapeRenderer;
    
    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;

    private boolean dragging;
    private float dragX, dragY;

    public Cube(Texture texture, float x, float y, float width, float height){
        this.texture = texture;
        bounds = new Rectangle(x, y, width, height);
        shapeRenderer = new ShapeRenderer();

        rectangle = new Rectangle(x, y, width, height);
    }

    public void render(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.end();

    }
    
    //Here is the function that can make the Cube be dragged from a side to another
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

        rectangle.setPosition(bounds.x, bounds.y);  //actualize the position of the invisible rectangle
    }
        
    public void dispose(){
        shapeRenderer.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public float getDragX() {
        return dragX;
    }

    public void setDragX(float dragX) {
        this.dragX = dragX;
    }

    public float getDragY() {
        return dragY;
    }

    public void setDragY(float dragY) {
        this.dragY = dragY;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    
}
