package com.mygdx.game;

public class LifeStrategy implements CharacterStrategy{

    @Override
    public void execute(Character character) {
        character.setLIFE(character.getLIFE() + 1000);
    }
    
}
