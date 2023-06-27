package com.mygdx.game.Strategies;

import com.mygdx.game.Player.Character;

public class TeleportStrategy implements CharacterStrategy{

    @Override
    public void execute(Character character) {
        // character "teleports"
        character.setSPEED(character.getSPEED() + 10000);
    }
    
}
