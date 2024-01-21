package com.mygdx.kotc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.kotc.KingOfTheCastle;
import com.mygdx.kotc.gamecontroller.GameControllerClient;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.PlayerTextureType;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;


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

    private Player selectedPlayer;


    private int CatWidth = 120;
    private int CatHeight = 250;


    private int startButton_Y = -80;
    private int startButtonHeight = 200;
    private int startButtonWidth = 120;

    private boolean isWizardCatActive = false;
    private boolean isMonkCatActive = false;
    private boolean isArcherCatActive = false;
    private boolean isKnightCatActive = false;


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
        kingOfTheCastle.font.draw(kingOfTheCastle.batch, "Whiskwizard",CatWidth,CatHeight+115);
        kingOfTheCastle.font.draw(kingOfTheCastle.batch, "Pawspective Monk",CatWidth+220,CatHeight+115);
        kingOfTheCastle.font.draw(kingOfTheCastle.batch, "PurrrowMaster",CatWidth+462,CatHeight+115);
        kingOfTheCastle.font.draw(kingOfTheCastle.batch, "Sir Whiskerlot",CatWidth+680,CatHeight+115);
        GlyphLayout layout = new GlyphLayout(kingOfTheCastle.thiccFont,"Select a Character");
        float textWidth = layout.width;
        kingOfTheCastle.thiccFont.draw(kingOfTheCastle.batch, "Select a Character",kingOfTheCastle.getScreenWidth() / 2 - textWidth/2 ,CatHeight+215);


        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < wizCat_Y + CatHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > wizCat_Y){
            kingOfTheCastle.batch.draw(wizardCat,x ,wizCat_Y, CatWidth, CatHeight);
            if(Gdx.input.isTouched()){
                selectedPlayer = PlayerFactory.createWizard();
                //DEMO player spawn (in final version through GameStates)
                selectedPlayer.setPlayerTextureType(PlayerTextureType.WIZARD_RIGHT);
                selectedPlayer.setPosition(new Vec2d(7,6));
                kingOfTheCastle.viewProxy.map.getTiles()[7][6].setOccupiedBy(selectedPlayer);

                isWizardCatActive = true;
                isMonkCatActive= false;
                isArcherCatActive=false;
                isKnightCatActive=false;
            }
        }else {
            if(!isWizardCatActive){
                kingOfTheCastle.batch.draw(wizardCatUnactive,x,wizCat_Y, CatWidth, CatHeight);
            }
            if(isWizardCatActive){
                kingOfTheCastle.batch.draw(wizardCat,x,wizCat_Y, CatWidth, CatHeight);
            }

        }

         x = kingOfTheCastle.getScreenWidth() / 2.56f   - CatWidth / 2;

        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < wizCat_Y + CatHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > wizCat_Y){
            kingOfTheCastle.batch.draw(monkCat,x ,wizCat_Y,CatWidth, CatHeight);
            if(Gdx.input.isTouched()){
                selectedPlayer = PlayerFactory.createMonk();
                isWizardCatActive = false;
                isMonkCatActive= true;
                isArcherCatActive=false;
                isKnightCatActive=false;
            }
        }else {
            if(!isMonkCatActive){
                kingOfTheCastle.batch.draw(monkCatUnactive,x,wizCat_Y,CatWidth, CatHeight);
            }
            if(isMonkCatActive){
                kingOfTheCastle.batch.draw(monkCat,x,wizCat_Y,CatWidth, CatHeight);
            }

        }


        x = kingOfTheCastle.getScreenWidth() / 1.63f  - CatWidth / 2f;

        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < wizCat_Y + CatHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > wizCat_Y){
            kingOfTheCastle.batch.draw(archerCat,x ,wizCat_Y,CatWidth, CatHeight);
            if(Gdx.input.isTouched()){
                selectedPlayer = PlayerFactory.createArcher();
                isWizardCatActive = false;
                isMonkCatActive= false;
                isArcherCatActive=true;
                isKnightCatActive=false;
            }
        }else {
            if(!isArcherCatActive){
                kingOfTheCastle.batch.draw(archerCatUnactive,x,wizCat_Y,CatWidth, CatHeight);
            }if(isArcherCatActive){
                kingOfTheCastle.batch.draw(archerCat,x,wizCat_Y,CatWidth, CatHeight);
            }

        }

        x = kingOfTheCastle.getScreenWidth() / 1.2f  - CatWidth / 2f;

        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < wizCat_Y + CatHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > wizCat_Y){
            kingOfTheCastle.batch.draw(knightCat,x ,wizCat_Y,CatWidth, CatHeight);
            if(Gdx.input.isTouched()){
                selectedPlayer = PlayerFactory.createknight();
                isWizardCatActive = false;
                isMonkCatActive= false;
                isArcherCatActive=false;
                isKnightCatActive=true;
            }
        }else {
            if(!isKnightCatActive){
                kingOfTheCastle.batch.draw(knightCatUnactive,x,wizCat_Y,CatWidth, CatHeight);
            }
            if(isKnightCatActive){
                kingOfTheCastle.batch.draw(knightCat,x,wizCat_Y,CatWidth, CatHeight);
            }

        }
        x = kingOfTheCastle.getScreenWidth() / 2f  - CatWidth / 2f;

        if(Gdx.input.getX() < x + CatWidth && Gdx.input.getX() > x
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() < startButton_Y + startButtonHeight
                && kingOfTheCastle.getScreenHeight() - Gdx.input.getY() > startButton_Y){
            kingOfTheCastle.batch.draw(startButton,x ,startButton_Y,CatWidth, CatHeight);
            if(Gdx.input.isTouched()) {

                kingOfTheCastle.gameControllerClient.setPlayer(selectedPlayer);


                kingOfTheCastle.setScreen(new MapScreen(kingOfTheCastle));
            }
        }else {
            kingOfTheCastle.batch.draw(startButtonUnactive,x,startButton_Y,CatWidth, CatHeight);
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
