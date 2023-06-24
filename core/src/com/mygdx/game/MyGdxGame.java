package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;


public class MyGdxGame extends ApplicationAdapter {

    private BackGround backGround;
    private Texture backGroundTexture, characterTexture, armTexture, cubeTexture, enemyTextureNormal, enemyTextureFast, enemyTextureBuff;
    private Obstacle mall, carV, carB, carO, carO2, carO3, p1, p2, p3, p4, garbage, barrier;
    private Character character;
    private HealthBar healthBar;
    private Arm arm;
    private Music backGroundMusic;
    private Cube cube;
    private CollisionManager collisionManager;
    private SpawnManager spawnManager;
    private SpriteBatch spriteBatch;
    private BitmapFont font;

    private ArrayList<Enemy> enemies;
    private float enemySpawnTimer; // Temporizador para controlar a frequência de geração
    private float enemySpawnInterval = 0.5f;


    @Override
    public void create () {
        spriteBatch = new SpriteBatch();
        collisionManager = new CollisionManager();
        spawnManager = new SpawnManager();

        //Score Board
        //---------------------------------------------------------------------------------
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(1.5f);

        //Background Music
        //---------------------------------------------------------------------------------
        backGroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/battle.wav"));
        backGroundMusic.setLooping(true);
        //backGroundMusic.play();

        //creating the backGround
        //---------------------------------------------------------------------------------
        backGroundTexture = new Texture("images/Fundo_Fase1.png");
        backGround = new BackGround(backGroundTexture);

        //creating the character
        //---------------------------------------------------------------------------------
        characterTexture = new Texture("images/character1.Right.png");
        character = new Character(characterTexture, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        healthBar = new HealthBar();
        armTexture = new Texture("images/Arm1.png");
        arm = new Arm(characterTexture, 0, 0, armTexture, 0, 0);

        //creating the cube
        //---------------------------------------------------------------------------------
        cubeTexture = new Texture("images/Cube.png");
        cube = new Cube(cubeTexture, 550, 570, 50, 50);

        //creating Enemys
        //---------------------------------------------------------------------------------
        enemyTextureNormal = new Texture("images/zombie.png");
        enemyTextureFast = new Texture("images/zombie2.png");
        enemyTextureBuff = new Texture("images/zombie3.png");

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

        //Creating spawn
        //---------------------------------------------------------------------------------
        enemies = new ArrayList<Enemy>();
        enemySpawnTimer = 0.0f;

    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 	//Set the screen back to black

        //Render Score
        //---------------------------------------------------------------------------------
        spriteBatch.begin();
        backGround.render();
        character.render();
        arm.shoot();
        healthBar.render(character);
        arm.render();
        font.draw(spriteBatch, "Score: " + character.getSCORE(), 10, Gdx.graphics.getHeight() - 10);
        cube.render();
        cube.handleInput();
        spriteBatch.end();
        


        //Updates
        //---------------------------------------------------------------------------------
        character.update();
        healthBar.update(character);
        arm.update(character);

        //Collision
        //---------------------------------------------------------------------------------
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

        //Spawn
        //---------------------------------------------------------------------------------
        enemySpawnTimer += Gdx.graphics.getDeltaTime();
        if(enemySpawnTimer >= enemySpawnInterval){
            Enemy enemy = spawnManager.spawnEnemy();
            enemies.add(enemy);
            character.addObserver(enemy);
            enemySpawnTimer = 0.0f;
        }

        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.render();
            enemy.update(character);
            collisionManager.checkCollision(character, enemy);
            collisionManager.checkCollision(enemy, cube);
            collisionManager.checkCollision(enemy, mall);
            collisionManager.checkCollision(enemy, carV);
            collisionManager.checkCollision(enemy, carB);
            collisionManager.checkCollision(enemy, carO);
            collisionManager.checkCollision(enemy, carO2);
            collisionManager.checkCollision(enemy, carO3);
            collisionManager.checkCollision(enemy, p1);
            collisionManager.checkCollision(enemy, p2);
            collisionManager.checkCollision(enemy, p3);
            collisionManager.checkCollision(enemy, p4);
            collisionManager.checkCollision(enemy, garbage);
            collisionManager.checkCollision(enemy, barrier);

            if (enemy.getLIFE() <= 0) {
                enemyIterator.remove();
                character.removeObserver(enemy);
                character.setSCORE(character.getSCORE() + 10);
                character.notifyObservers();
                // O inimigo foi derrotado, faça o que for necessário aqui
            }
        }

        for(Bullet bullet : arm.getBullets()){
            enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                collisionManager.checkCollision(enemy, bullet);
            }
        }

    }

    @Override
    public void dispose () {
        backGroundTexture.dispose();
        characterTexture.dispose();
        armTexture.dispose();
        cubeTexture.dispose();
        enemyTextureNormal.dispose();
        enemyTextureFast.dispose();
        enemyTextureBuff.dispose();
        backGroundMusic.dispose();
        spriteBatch.dispose();
    }
}
