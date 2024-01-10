package com.mygdx.kotc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.kotc.KingOfTheCastle;

public class StartScreen implements Screen {


    private int exitButtonWidth = 250;
    private int exitButtonHeight = 120;
    private int playButtonWidth = 300;
    private int playButtonheight = 120;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 230;

    private static final int LOGO_WIDTH = 400;
    private static final int LOGO_HEIGHT = 250;
    private static final int LOGO_Y = 450;

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

        int x = kingOfTheCastle.getScreenWidth() / 2 - exitButtonWidth / 2;

        if(Gdx.input.getX() < x + exitButtonWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < EXIT_BUTTON_Y + exitButtonHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > EXIT_BUTTON_Y){
            kingOfTheCastle.batch.draw(exitButtonActive,x ,EXIT_BUTTON_Y,exitButtonWidth, exitButtonHeight);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }else {
            kingOfTheCastle.batch.draw(exitButtonInactive,x,EXIT_BUTTON_Y,exitButtonWidth, exitButtonHeight);
        }


        x = kingOfTheCastle.getScreenWidth()/2 - playButtonWidth /2;

        if(Gdx.input.getX() < x + playButtonWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < PLAY_BUTTON_Y + playButtonheight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY()>PLAY_BUTTON_Y){
            kingOfTheCastle.batch.draw(playButtonInactive,x,PLAY_BUTTON_Y,playButtonWidth,playButtonheight);
            if(Gdx.input.isTouched()){
               //kingOfTheCastle.setScreen();
            }
        }else {
            kingOfTheCastle.batch.draw(playButtonActive,x,PLAY_BUTTON_Y,playButtonWidth,playButtonheight);
        }





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
