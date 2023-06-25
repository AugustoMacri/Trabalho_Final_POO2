package com.mygdx.game;

public class TeleportStrategy implements CharacterStrategy{

    @Override
    public void execute(Character character) {
        character.setSPEED(character.getSPEED() + 10000);
    }
    
}
