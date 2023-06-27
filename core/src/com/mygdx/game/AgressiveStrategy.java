package com.mygdx.game;

public class AgressiveStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        enemy.setSPEED(enemy.getSPEED() + 1);
        enemy.setLIFE(enemy.getLIFE() + 15);
    }
    
}
