package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class SpawnManager {

    Texture enemyTextureNormal = new Texture("images/zombie.png");
    Texture enemyTextureFast = new Texture("images/zombie2.png");
    Texture enemyTextureBuff = new Texture("images/zombie3.png");

    public SpawnManager() {

    }

    public Enemy spawnEnemy() {
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
        return enemy;
    }
}
