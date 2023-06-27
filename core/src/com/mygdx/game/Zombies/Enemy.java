package com.mygdx.game.Zombies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Player.Character;
import com.mygdx.game.Strategies.AgressiveStrategy;
import com.mygdx.game.Strategies.AngryStrategy;
import com.mygdx.game.Strategies.EnemyStrategy;
import com.mygdx.game.Strategies.HardcoreStrategy;
import com.mygdx.game.Strategies.NormalStrategy;
import com.badlogic.gdx.math.Rectangle;
import java.lang.Math;
import java.util.List;

public abstract class Enemy implements ScoreObserver{
    protected Texture texture;
    protected float x, y, width, height;
    protected SpriteBatch batch;
    protected Rectangle rectangle;
    protected Vector2 previousPosition;
    protected int SPEED;
    protected int LIFE;
    protected int DANO;
    protected int DAMAGEBULLET;
    protected EnemyStrategy enemyStrategy;

    public Enemy(Texture texture, float x, float y, float width, float height, int SPEED, int LIFE, int DANO, int DAMAGEBULLET){
        batch = new SpriteBatch();
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.SPEED = SPEED;
        this.LIFE = LIFE;
        this.DANO = DANO;
        this.DAMAGEBULLET = DAMAGEBULLET;
        enemyStrategy = new NormalStrategy();

        rectangle = new Rectangle(x, y, 25, 50);
    }

    public void update(Character character){
        if(LIFE > 0) {
            previousPosition = new Vector2(x, y);

            // Zombie movement
            //------------------------------------------------------------
            if (x != character.getPlayerPositionX() || y != character.getPlayerPositionY()) {
                float deltaX = character.getPlayerPositionX() - x;
                float deltaY = character.getPlayerPositionY() - y;
                float angle = (float) Math.atan2(deltaY, deltaX);   // Calculate the angle between the character and the zombie

                // Calculate the new x and y coordinates based on the angle and speed
                float newX = (float) (x + SPEED * Math.cos(angle) * Gdx.graphics.getDeltaTime()); // Math.cos(angle) provides the x component of the enemy movement along the angle
                float newY = (float) (y + SPEED * Math.sin(angle) * Gdx.graphics.getDeltaTime()); // Math.sin(angle) provides the y component of the enemy movement along the angle

                x = newX;
                y = newY;
            }

            // Update collision rectangle position
            //------------------------------------------------------------
            rectangle.setPosition(x, y);
        }
    }


    public void setStrategy(EnemyStrategy enemyStrategy){
        this.enemyStrategy = enemyStrategy;
    }

    public void executeStrategy(EnemyStrategy enemyStrategy){
        enemyStrategy.execute(this);
    }

    public void render(){
        if(LIFE > 0) {
            batch.begin();
            batch.draw(texture, x - 20, y, 64, 64);
            batch.end();
        }
    }

    @Override
    public void updateScore(int SCORE){

        // Enemies observe the character's score and change their strategy
        if(SCORE >= 1000){
            setStrategy(new HardcoreStrategy());
            enemyStrategy.execute(this);
        }else if (SCORE >= 500){
            setStrategy(new AgressiveStrategy());
            enemyStrategy.execute(this);
        }else if (SCORE >= 250){
            setStrategy(new AngryStrategy());
            enemyStrategy.execute(this);
        }else if(SCORE == 0){
            enemyStrategy.execute(this);
        }
    }

    //TENTAR COLOCAR COM O OU ||

    public int getDAMAGEBULLET() {
        return DAMAGEBULLET;
    }

    public void setDAMAGEBULLET(int DAMAGEBULLET) {
        this.DAMAGEBULLET = DAMAGEBULLET;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Vector2 getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(Vector2 previousPosition) {
        this.previousPosition = previousPosition;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int sPEED) {
        SPEED = sPEED;
    }

    public int getLIFE() {
        return LIFE;
    }

    public void setLIFE(int lIFE) {
        LIFE = lIFE;
    }

    public int getDANO() {
        return DANO;
    }

    public void setDANO(int dANO) {
        DANO = dANO;
    }

    public EnemyStrategy getEnemyStrategy() {
        return enemyStrategy;
    }

    public void setEnemyStrategy(EnemyStrategy enemyStrategy) {
        this.enemyStrategy = enemyStrategy;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    
    
    
}
