package com.mygdx.game;

public class AngryStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        enemy.setDANO(enemy.getDANO());
        enemy.setSPEED(enemy.getSPEED());
    }
    
}
