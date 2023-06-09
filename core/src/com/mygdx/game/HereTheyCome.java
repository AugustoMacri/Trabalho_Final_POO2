package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Database.ConnectionDAO;
import com.mygdx.game.Database.ScriptsDAO;
import com.mygdx.game.Managers.CollisionManager;
import com.mygdx.game.Managers.SpawnManager;
import com.mygdx.game.Player.Arm;
import com.mygdx.game.Player.Bullet;
import com.mygdx.game.Player.Character;
import com.mygdx.game.Player.HealthBar;
import com.mygdx.game.Player.SkillBar;
import com.mygdx.game.World.BackGround;
import com.mygdx.game.World.Cube;
import com.mygdx.game.World.Obstacle;
import com.mygdx.game.Zombies.Enemy;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;


public class HereTheyCome extends ApplicationAdapter {

    private BackGround backGround;
    private Texture backGroundTexture, characterTexture, armTexture, cubeTexture, enemyTextureNormal, enemyTextureFast, enemyTextureBuff;
    private Obstacle mall, carV, carB, carO, carO2, carO3, p1, p2, p3, p4, garbage, barrier;
    private Character character;
    private HealthBar healthBar;
    private SkillBar skillbar;
    private Arm arm;
    private Music backGroundMusic, menuMusic, gameOverMusic;
    private Cube cube;
    private CollisionManager collisionManager;
    private SpawnManager spawnManager;
    private SpriteBatch spriteBatch;
    private BitmapFont font;

    private ArrayList<Enemy> enemies;
    private float enemySpawnTimer; 
    private float enemySpawnInterval = 0.5f;

    private boolean gameRunning = false;

    private boolean gameOver = false;

    private ScriptsDAO scriptsDAO;
    private ConnectionDAO connectionDAO;

    private int topScore;

    public void gameMenu() {

        //creating the map
        //---------------------------------------------------------------------------------
        backGroundTexture = new Texture("images/Menu.png");
        backGround = new BackGround(backGroundTexture);
    }

    public void gameMusic() {

        // creating menu, gameplay and game over music
        //---------------------------------------------------------------------------------
        backGroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/Background.wav"));
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/Menu_music.wav"));
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/game_over.wav"));
    }

    public void createScoreTable() {
        connectionDAO = new ConnectionDAO();
        scriptsDAO = new ScriptsDAO();
        try {
            connectionDAO.connect();
            scriptsDAO.createTable(connectionDAO.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create () {

        // checks if the music are instantiated
        if (backGroundMusic == null || menuMusic == null || gameOverMusic == null) {
            gameMusic();
        }
        // checks if the database connection and scripts are set
        if (connectionDAO == null || scriptsDAO == null) {
            createScoreTable();
        }


        spriteBatch = new SpriteBatch();
        collisionManager = new CollisionManager();
        spawnManager = new SpawnManager();

        // Score
        //---------------------------------------------------------------------------------
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2.0f);

        //creating the character
        //---------------------------------------------------------------------------------
        characterTexture = new Texture("images/character1.Right.png");
        character = new Character(characterTexture, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        healthBar = new HealthBar();
        skillbar = new SkillBar();
        armTexture = new Texture("images/Arm1.png");
        arm = new Arm(characterTexture, 0, 0, armTexture, 0, 0);

        //creating the defensive cube
        //---------------------------------------------------------------------------------
        cubeTexture = new Texture("images/Cube.png");
        cube = new Cube(cubeTexture, 550, 570, 50, 50);


        //Creating Obstacles on the map
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

        if (gameRunning) {
            if (gameOver) {
                renderGameOverScreen();
            } else {
                renderGame();
            }
        } else {
            gameMenu();
            renderBackgroundOnly();
        }
    }

    private void renderBackgroundOnly() {

        if (!menuMusic.isPlaying()) {  // Checks the music and sets the right one (menu)
            menuMusic.play();
            menuMusic.setLooping(true);
            menuMusic.setVolume(0.3f);
        }

        spriteBatch.begin();
        backGround.render();
        spriteBatch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameRunning = true;
            gameOver = false;
            backGroundTexture = new Texture("images/Fundo_Fase1.png");  // Changes to the actual map background
            backGround.setTexture(backGroundTexture);
            menuMusic.stop();
            backGroundMusic.play(); // play the gameplay song
            backGroundMusic.setLooping(true);
            backGroundMusic.setVolume(0.3f);
        }
    }
    
    private void resetGame() {
        create();
        backGroundTexture = new Texture("images/Fundo_Fase1.png");
        backGround.setTexture(backGroundTexture);
        gameOverMusic.stop();
        backGroundMusic.play();
        backGroundMusic.setLooping(true);
        backGroundMusic.setVolume(0.3f);
        renderGame(); // The game is ready to play again
    }

    public void renderGameOverScreen() {

        if(!gameOverMusic.isPlaying()) { // Checks the music and sets the right one (game over)
            backGroundMusic.stop();
            gameOverMusic.play();
            gameOverMusic.setLooping(true);
            gameOverMusic.setVolume(0.3f);
        }

        backGroundTexture = new Texture("images/gameOver_screen.png");  // Changes to the actual map background
        backGround.setTexture(backGroundTexture);
        spriteBatch.begin();
        backGround.render();
        try {
            topScore = scriptsDAO.topScore(connectionDAO.getConnection(), character); // Select the top score on the database to be shown on the screen
        } catch (SQLException e) {
            e.printStackTrace();
        }
        font.draw(spriteBatch, "" + character.getSCORE(), Gdx.graphics.getWidth()/2 + 50, Gdx.graphics.getHeight()/2 + 115);
        font.draw(spriteBatch, "" + topScore, Gdx.graphics.getWidth()/2 + 50, Gdx.graphics.getHeight()/2 + 23);
        spriteBatch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameRunning = true;
            gameOver = false;
            resetGame();
        }
    }


    public void renderGame () {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 	//Set the screen back to black

        //Render Score
        //---------------------------------------------------------------------------------
        spriteBatch.begin();
        backGround.render();
        character.render();
        arm.shoot();
        healthBar.render(character);
        skillbar.render(character);
        arm.render();
        // Shows the current player's score in the top left corner
        font.draw(spriteBatch, "Score: " + character.getSCORE(), 10, Gdx.graphics.getHeight() - 10);
        cube.render();
        cube.handleInput();
        spriteBatch.end();
        


        //Updates
        //---------------------------------------------------------------------------------
        character.update();
        healthBar.update(character);
        skillbar.update(character);
        arm.update(character);
        character.skills();

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
            Enemy enemy = spawnManager.spawnEnemy(); // A zombie spawns every 0.5sec
            enemies.add(enemy); // Adding it to the arraylist to iterate
            character.addObserver(enemy); // Adding it to be a score observer
            enemySpawnTimer = 0.0f;
        }

        Iterator<Enemy> enemyIterator = enemies.iterator();
        // Iterates with the zombies, checking collision with every object on the map
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

            // if a zombie dies, it will be removed to the arraylist and the player's score increase, notifying observers
            if (enemy.getLIFE() <= 0) {
                enemyIterator.remove();
                character.removeObserver(enemy);
                character.setSCORE(character.getSCORE() + 10);
                character.notifyObservers();
            }
        }

        // Checking collision between the zombies and the shots
        for(Bullet bullet : arm.getBullets()){
            enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                collisionManager.checkCollision(enemy, bullet);
            }
        }

        // if the character dies, the game over screen is shown and the score is stored on the database
        if(character.getLIFE() <= 0) {
            gameOver = true;
            try {
                scriptsDAO.insertTable(connectionDAO.getConnection(), character);
            }catch(SQLException e){
                throw new RuntimeException();
            }
            return;
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
