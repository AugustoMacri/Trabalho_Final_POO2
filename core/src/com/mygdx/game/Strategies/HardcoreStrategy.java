package com.mygdx.game.Strategies;

import com.mygdx.game.Zombies.Enemy;

public class HardcoreStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        enemy.setLIFE(enemy.getLIFE() + 20);
    }
    
}
