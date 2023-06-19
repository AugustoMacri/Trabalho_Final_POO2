package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackGround {
        private Texture texture;
        private SpriteBatch batch;

    public BackGround(Texture texture) {
        this.texture = texture;
        batch = new SpriteBatch();
    }

    public void render(){
        batch.begin();
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }
}
