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

import java.awt.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;



public class MyGdxGame extends ApplicationAdapter {

    Texture characterTexture, cubeTexture, bulletTexture;
    
    Character character;
    Bullet bullet;
    Cube cube;
<<<<<<< Updated upstream

    SpriteBatch spriteBatch;
<<<<<<< Updated upstream
    //ArrayList<Bullet> bullets = new ArrayList<>();
=======
    public static List<Enemy> enemies = new ArrayList<>();
>>>>>>> Stashed changes
=======
    CollisionManager collisionManager = new CollisionManager();
    SpriteBatch spriteBatch;
    public static List<Enemy> enemies = new ArrayList<>();
>>>>>>> Stashed changes



    @Override
    public void create () {

        //creating the character
        //---------------------------------------------------------------------------------
        characterTexture = new Texture("character1.Right.png");
        bulletTexture = new Texture("bullet.png");
        character = new Character(characterTexture, 0, 0);
        spriteBatch = new SpriteBatch();
        bullet = new Bullet(character, bulletTexture);




        //creating the cube
        //---------------------------------------------------------------------------------
        cubeTexture = new Texture("Cube.png"); //Still not working!!!!
        cube = new Cube(cubeTexture, 150, 150, 50, 50);
<<<<<<< Updated upstream
        spriteBatch = new SpriteBatch();
=======
        
        //creating Enemys
        //---------------------------------------------------------------------------------
        enemyTextureNormal = new Texture("images/zombie.png");
<<<<<<< Updated upstream
        enemyNormal = new ZombieNormal(enemyTextureNormal, 200, 200, 50, 50);
        enemies.add(enemyNormal);
        enemyNormal.spawnEnemies();
        enemyTextureFast = new Texture("images/zombie2.png");
        enemyFast = new ZombieFast(enemyTextureFast, 200, 200, 50, 50);
        enemies.add(enemyFast);
        enemyFast.spawnEnemies();
        enemyTextureBuff = new Texture("images/zombie3.png");
        enemyBuff = new ZombieBuff(enemyTextureBuff, 200, 200, 50, 50);
        enemies.add(enemyBuff);
        enemyBuff.spawnEnemies();
>>>>>>> Stashed changes

=======
//        enemyNormal = new ZombieNormal(enemyTextureNormal, 200, 200, 50, 50);
//        //enemies.add(enemyNormal);
        ZombieNormal.spawnEnemies(enemyTextureNormal);
        enemyTextureFast = new Texture("images/zombie2.png");
//        enemyFast = new ZombieFast(enemyTextureFast, 200, 200, 50, 50);
        //enemies.add(enemyFast);
        ZombieFast.spawnEnemies(enemyTextureFast);
        enemyTextureBuff = new Texture("images/zombie3.png");
//        enemyBuff = new ZombieBuff(enemyTextureBuff, 200, 200, 50, 50);
        //enemies.add(enemyBuff);
        ZombieBuff.spawnEnemies(enemyTextureBuff);
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
>>>>>>> Stashed changes

    }

    @Override
    public void render () { 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 	//Set de screen back at black

        spriteBatch.begin();
        if((Gdx.input.isKeyPressed(Input.Keys.SPACE))){
            bullet.generateBullet();
            }
        bullet.render();
        character.render();
<<<<<<< Updated upstream
=======
        arm.shoot();
        healthBar.render(character);
        arm.render();
<<<<<<< Updated upstream
        enemyNormal.render();
        enemyBuff.render();
        enemyFast.render();
>>>>>>> Stashed changes
=======
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.render();
            enemy.update(character);
            if (enemy.shouldBeRemoved()) {
                iterator.remove();
            }
        }

>>>>>>> Stashed changes
        spriteBatch.end();
        cube.render();
        cube.handleInput();
        character.update();
<<<<<<< Updated upstream
        character.detectCollision(cube);
=======
        healthBar.update(character);
        arm.update(character);
<<<<<<< Updated upstream
        enemyNormal.update(character);
        enemyBuff.update(character);
        enemyFast.update(character);
=======
>>>>>>> Stashed changes


        //Collision
        //---------------------------------------------------------------------------------
//        collisionManager.checkCollision(character, enemyNormal);
//        collisionManager.checkCollision(character, enemyFast);
//        collisionManager.checkCollision(character, enemyBuff);
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
<<<<<<< Updated upstream
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

>>>>>>> Stashed changes
=======
//        collisionManager.checkCollision(enemyNormal, mall);
//        collisionManager.checkCollision(enemyNormal, carV);
//        collisionManager.checkCollision(enemyNormal, carB);
//        collisionManager.checkCollision(enemyNormal, carO);
//        collisionManager.checkCollision(enemyNormal, carO2);
//        collisionManager.checkCollision(enemyNormal, carO3);
//        collisionManager.checkCollision(enemyNormal, p1);
//        collisionManager.checkCollision(enemyNormal, p2);
//        collisionManager.checkCollision(enemyNormal, p3);
//        collisionManager.checkCollision(enemyNormal, p4);
//        collisionManager.checkCollision(enemyNormal, garbage);
//        collisionManager.checkCollision(enemyNormal, barrier);
//        collisionManager.checkCollision(enemyFast, mall);
//        collisionManager.checkCollision(enemyFast, carV);
//        collisionManager.checkCollision(enemyFast, carB);
//        collisionManager.checkCollision(enemyFast, carO);
//        collisionManager.checkCollision(enemyFast, carO2);
//        collisionManager.checkCollision(enemyFast, carO3);
//        collisionManager.checkCollision(enemyFast, p1);
//        collisionManager.checkCollision(enemyFast, p2);
//        collisionManager.checkCollision(enemyFast, p3);
//        collisionManager.checkCollision(enemyFast, p4);
//        collisionManager.checkCollision(enemyFast, garbage);
//        collisionManager.checkCollision(enemyFast, barrier);
//        collisionManager.checkCollision(enemyBuff, mall);
//        collisionManager.checkCollision(enemyBuff, carV);
//        collisionManager.checkCollision(enemyBuff, carB);
//        collisionManager.checkCollision(enemyBuff, carO);
//        collisionManager.checkCollision(enemyBuff, carO2);
//        collisionManager.checkCollision(enemyBuff, carO3);
//        collisionManager.checkCollision(enemyBuff, p1);
//        collisionManager.checkCollision(enemyBuff, p2);
//        collisionManager.checkCollision(enemyBuff, p3);
//        collisionManager.checkCollision(enemyBuff, p4);
//        collisionManager.checkCollision(enemyBuff, garbage);
//        collisionManager.checkCollision(enemyBuff, barrier);
//        collisionManager.checkCollision(enemyNormal, cube);
//        collisionManager.checkCollision(enemyFast, cube);
//        collisionManager.checkCollision(enemyBuff, cube);
>>>>>>> Stashed changes
    }

    @Override
    public void dispose(){
        bullet.dispose();
        character.dispose();
        spriteBatch.dispose();
<<<<<<< Updated upstream
        cube.dispose();
=======
        backGroundMusic.dispose();
>>>>>>> Stashed changes
    }
}
