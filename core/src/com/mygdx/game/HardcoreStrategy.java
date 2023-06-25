package com.mygdx.game;

public class HardcoreStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        enemy.setDANO(enemy.getDANO() * 3);
        enemy.setSPEED(enemy.getSPEED() * 3);
    }
    
}
