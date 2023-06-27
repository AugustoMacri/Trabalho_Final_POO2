package com.mygdx.game.Strategies;

import com.mygdx.game.Player.Character;

public class LifeStrategy implements CharacterStrategy{

    @Override
    public void execute(Character character) {
        // the character heals 1000 life points
        character.setLIFE(character.getLIFE() + 1000);
    }
    
}
