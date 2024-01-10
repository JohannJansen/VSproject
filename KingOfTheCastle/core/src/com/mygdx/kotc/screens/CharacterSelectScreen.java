package com.mygdx.kotc.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
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


    public CharacterSelectScreen() {
        this.wizardCat = new Texture("OrangeWizardCat");
        this.knightCat = new Texture("BlackAndWhiteKnightCat");
        this.monkCat =   new Texture("OrangeMonkCat");
        this.archerCat = new Texture("GreyArcherCat");
        this.wizardCatUnactive = new Texture("OrangeWizardCatUnactive");
        this.knightCatUnactive = new Texture("BlackAndWhiteKnightCatUnactive");
        this.monkCatUnactive =   new Texture("OrangeMonkCatUnactive");
        this.archerCatUnactive = new Texture("GreyArcherCatUnactive");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
