package com.mygdx.game;

public class NormalStrategy implements EnemyStrategy{

    @Override
    public void execute(Enemy enemy) {
        enemy.setSPEED(enemy.getSPEED()/2);
        enemy.setDANO(enemy.getDANO()/2);
    }
    
}
