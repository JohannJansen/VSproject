package com.mygdx.kotc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.kotc.KingOfTheCastle;


public class CharacterSelectScreen implements Screen {

    private KingOfTheCastle kingOfTheCastle;

    Texture wizardCat;
    Texture knightCat;
    Texture monkCat;
    Texture archerCat;
    Texture wizardCatUnactive;
    Texture knightCatUnactive;
    Texture monkCatUnactive;
    Texture archerCatUnactive;
    Texture startButton;
    Texture startButtonUnactive;

    private int CatWidth = 120;
    private int CatHeight = 250;

    private int monkCatWidth = 120;
    private int monkCatHeight = 250;

    private int startButton_Y = -80;
    private int startButtonHeight = 200;
    private int startButtonWidth = 120;


    private static final int wizCat_Y = 100;
    private BitmapFont font;
    private String wizstring = "WizardCat";




    public CharacterSelectScreen(KingOfTheCastle kingOfTheCastle) {
        this.kingOfTheCastle = kingOfTheCastle;
        this.wizardCat = new Texture("OrangeWizardCat.png");
        this.knightCat = new Texture("BlackAndWhiteKnightCat.png");
        this.monkCat =   new Texture("OrangeMonkCat.png");
        this.archerCat = new Texture("GreyArcherCat.png");
        this.wizardCatUnactive = new Texture("OrangeWizardCatUnactive.png");
        this.knightCatUnactive = new Texture("BlackAndWhiteKnightCatUnactive.png");
        this.monkCatUnactive =   new Texture("OrangeMonkCatUnactive.png");
        this.archerCatUnactive = new Texture("GreyArcherCatUnactive.png");
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

        float x = kingOfTheCastle.getScreenWidth() / 6  - CatWidth / 2;


        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < wizCat_Y + CatHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > wizCat_Y){
            kingOfTheCastle.batch.draw(wizardCat,x ,wizCat_Y, CatWidth, CatHeight);
            if(Gdx.input.isTouched()){
               //todo : go to game with selected character
            }
        }else {
            kingOfTheCastle.batch.draw(wizardCatUnactive,x,wizCat_Y, CatWidth, CatHeight);
        }

         x = kingOfTheCastle.getScreenWidth() / 2.56f   - monkCatWidth / 2;

        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < wizCat_Y + CatHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > wizCat_Y){
            kingOfTheCastle.batch.draw(monkCat,x ,wizCat_Y,monkCatWidth, monkCatHeight);
            if(Gdx.input.isTouched()){
                //todo : go to game with selected character
            }
        }else {
            kingOfTheCastle.batch.draw(monkCatUnactive,x,wizCat_Y,monkCatWidth, monkCatHeight);
        }

        x = kingOfTheCastle.getScreenWidth() / 1.63f  - monkCatWidth / 2f;

        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < wizCat_Y + CatHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > wizCat_Y){
            kingOfTheCastle.batch.draw(archerCat,x ,wizCat_Y,monkCatWidth, monkCatHeight);
            if(Gdx.input.isTouched()){
                //todo : go to game with selected character
            }
        }else {
            kingOfTheCastle.batch.draw(archerCatUnactive,x,wizCat_Y,monkCatWidth, monkCatHeight);
        }

        x = kingOfTheCastle.getScreenWidth() / 1.2f  - monkCatWidth / 2f;

        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < wizCat_Y + CatHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > wizCat_Y){
            kingOfTheCastle.batch.draw(knightCat,x ,wizCat_Y,monkCatWidth, monkCatHeight);
            if(Gdx.input.isTouched()){
                //todo : go to game with selected character
            }
        }else {
            kingOfTheCastle.batch.draw(knightCatUnactive,x,wizCat_Y,monkCatWidth, monkCatHeight);
        }

        x = kingOfTheCastle.getScreenWidth() / 2f  - monkCatWidth / 2f;

        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < startButton_Y + startButtonHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > startButton_Y){
            kingOfTheCastle.batch.draw(startButton,x ,startButton_Y,monkCatWidth, monkCatHeight);
            if(Gdx.input.isTouched()){
                //todo : go to game with selected character
            }
        }else {
            kingOfTheCastle.batch.draw(startButtonUnactive,x,startButton_Y,monkCatWidth, monkCatHeight);
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
