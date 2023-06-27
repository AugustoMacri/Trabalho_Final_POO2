package com.mygdx.game.Strategies;

import com.mygdx.game.Zombies.Enemy;

public class NormalStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        enemy.setSPEED(enemy.getSPEED());
        enemy.setDANO(enemy.getDANO());
    }
    
}
