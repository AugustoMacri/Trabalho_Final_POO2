package com.mygdx.game;

public class AgressiveStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        enemy.setDANO(enemy.getDANO() * 2);
        enemy.setSPEED(enemy.getSPEED() * 2);
    }
    
}
