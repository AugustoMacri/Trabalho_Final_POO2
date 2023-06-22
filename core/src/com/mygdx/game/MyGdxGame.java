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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;


public class MyGdxGame extends ApplicationAdapter {

    private Music backGroundMusic;
    Texture backGroundTexture, characterTexture, armTexture, cubeTexture, enemyTextureNormal, enemyTextureFast, enemyTextureBuff, enemyTexture;
    BackGround backGround;
    Obstacle mall, carV, carB, carO, carO2, carO3, p1, p2, p3, p4, garbage, barrier;
    Character character;
    Arm arm;
    HealthBar healthBar;
    Cube cube;
    CollisionManager collisionManager = new CollisionManager();
    SpriteBatch spriteBatch;

    private List<Enemy> enemies; // Lista de inimigos gerados
    private float enemySpawnTimer; // Temporizador para controlar a frequência de geração
    private float enemySpawnInterval = 2.0f;



    @Override
    public void create () {
        spriteBatch = new SpriteBatch();

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
        armTexture = new Texture("images/gun.png");
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

        //Render
        //---------------------------------------------------------------------------------
        spriteBatch.begin();
        backGround.render();
        character.render();
        arm.shoot();
        healthBar.render(character);
        arm.render();
        spriteBatch.end();
        cube.render();
        cube.handleInput();


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
            spawnEnemy();
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
                // O inimigo foi derrotado, faça o que for necessário aqui
            }
        }

        for(Bullet bullet : arm.getBullets()){
            enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                collisionManager.checkCollision(enemy, bullet);
//                if (bullet.getLIFE() <= 0) {
//                    bullet.setActive(false);
//                    // A bala não está mais ativa, faça o que for necessário aqui
//                }
            }
        }

    }

    private void spawnEnemy() {
        Random random = new Random();
        int enemyType = random.nextInt(3); // Randomly select enemy type (0, 1, or 2)

        Texture enemyTexture;

        // Determine the enemy texture based on enemy type
        if (enemyType == 0) {
            enemyTexture = enemyTextureNormal;
        } else if (enemyType == 1) {
            enemyTexture = enemyTextureFast;
        } else {
            enemyTexture = enemyTextureBuff;
        }

        float x = random.nextInt(Gdx.graphics.getWidth());
        float y = random.nextInt(Gdx.graphics.getHeight());

        Enemy enemy;

        // Create enemy instance based on enemy type
        if (enemyType == 0) {
            enemy = new ZombieNormal(enemyTexture, x, y, 64, 64, 20, 100, 5, 70);
        } else if (enemyType == 1) {
            enemy = new ZombieFast(enemyTexture, x, y, 64, 64, 50, 65, 1, 40);
        } else {
            enemy = new ZombieBuff(enemyTexture, x, y, 64, 64, 20, 150, 10, 100);
        }

        enemies.add(enemy);
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
