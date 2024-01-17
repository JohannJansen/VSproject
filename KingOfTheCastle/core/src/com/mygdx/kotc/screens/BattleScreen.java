package com.mygdx.kotc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.kotc.KingOfTheCastle;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;


public class BattleScreen implements Screen {

    private KingOfTheCastle kingOfTheCastle;
    Texture startButton;
    Texture startButtonUnactive;

    private Player selectedPlayer;


    private int startButton_Y = -80;
    private int startButtonHeight = 200;
    private int startButtonWidth = 120;

    private BitmapFont font;

    public BattleScreen(KingOfTheCastle kingOfTheCastle) {
        this.kingOfTheCastle = kingOfTheCastle;
        this.startButton = new Texture("startButton.png");
        this.startButtonUnactive = new Texture("startButtonUnactive.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1 ,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        kingOfTheCastle.batch.begin();

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
