package com.mygdx.game.Strategies;

import com.mygdx.game.Player.Character;

public class LifeStrategy implements CharacterStrategy{

    @Override
    public void execute(Character character) {
        character.setLIFE(character.getLIFE() + 1000);
    }
    
}
