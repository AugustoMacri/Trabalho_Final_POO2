package com.mygdx.game;

public class HardcoreStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        enemy.setLIFE(enemy.getLIFE() + 20);
    }
    
}
