package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class SpawnManager {

    Texture enemyTextureNormal = new Texture("images/zombie.png");
    Texture enemyTextureFast = new Texture("images/zombie2.png");
    Texture enemyTextureBuff = new Texture("images/zombie3.png");
    ZombieBuffFac zombieBuffFac = new ZombieBuffFac();
    ZombieFastFac zombieFastFac = new ZombieFastFac();
    ZombieNormalFac zombieNormalFac = new ZombieNormalFac();

    public SpawnManager() {

    }

    public Enemy spawnEnemy() {
        Random random = new Random();
        int enemyType = random.nextInt(3); // Randomly select enemy type (0, 1, or 2)

        Texture enemyTexture;
        float x = random.nextInt(Gdx.graphics.getWidth());
        float y = random.nextInt(Gdx.graphics.getHeight());
        Enemy enemy = null;

        switch(enemyType) {
            case 0: enemyTexture = enemyTextureNormal;
                    enemy = zombieNormalFac.createZombie(enemyTexture, x, y);
                    break;
            case 1: enemyTexture = enemyTextureFast;
                    enemy = zombieFastFac.createZombie(enemyTexture, x, y);
                    break;
            case 2: enemyTexture = enemyTextureBuff;
                    enemy = zombieBuffFac.createZombie(enemyTexture, x, y);
                    break;
        }

        return enemy;
        
    }
}
