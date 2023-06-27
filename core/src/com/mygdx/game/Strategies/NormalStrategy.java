package com.mygdx.game.Strategies;

import com.mygdx.game.Zombies.Enemy;

public class NormalStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        // 1st strategy, the zombies stats are default
        enemy.setSPEED(enemy.getSPEED());
        enemy.setDANO(enemy.getDANO());
    }
    
}
