package com.mygdx.kotc.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.kotc.KingOfTheCastle;

public class StartScreen implements Screen {

    private  KingOfTheCastle kingOfTheCastle;

    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;

    public StartScreen(KingOfTheCastle kingOfTheCastle) {
        this.kingOfTheCastle = kingOfTheCastle;
        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_inactive.png");
        exitButtonInactive = new Texture("exit_button_active.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        kingOfTheCastle.batch.begin();

        kingOfTheCastle.batch.draw(playButtonActive,100,100);
        kingOfTheCastle.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
