package com.mygdx.game;

public class AngryStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        enemy.setDANO(enemy.getDANO() + 1);
        enemy.setSPEED(enemy.getSPEED() + 1);
        enemy.setLIFE(enemy.getLIFE() + 10);
    }
    
}
