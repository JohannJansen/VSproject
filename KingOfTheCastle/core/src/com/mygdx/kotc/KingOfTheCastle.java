package com.mygdx.kotc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.kotc.gamecontroller.GameControllerClient;
import com.mygdx.kotc.screens.MapScreen;
import com.mygdx.kotc.screens.StartScreen;
import com.mygdx.kotc.viewproxy.ViewProxy;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KingOfTheCastle extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public BitmapFont thiccFont;
    private Random random;
    public static final int TEXTUREHEIGHT = 32;
    public static final int TEXTUREWIDTH = 32;
    public ViewProxy viewProxy;
    MapScreen mapScreen;
    private int screenWidth = 1024;
    private int screenHeight = 1024;

    public GameControllerClient gameControllerClient;

    @Override
    public void create() {
        startClient();

        viewProxy = gameControllerClient.getViewProxy();
        //tileRenderDataList  = viewProxy.mapToTileRenderData();
        Gdx.graphics.setWindowedMode(getScreenWidth(), getScreenHeight());
        batch = new SpriteBatch();
        random = new Random();
        font = new BitmapFont();
        thiccFont = new BitmapFont();
        font.setColor(Color.BLACK);
        thiccFont.setColor(Color.BLACK);
        thiccFont.getData().scale(1.0f);
        this.setScreen(new StartScreen(this));
        //this.setScreen(new CharacterSelectScreen(this));
        //this.setScreen(new MapScreen(this));
    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        thiccFont.dispose();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

	public void startClient(){
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		gameControllerClient = new GameControllerClient();
		executorService.submit(gameControllerClient::run);
		executorService.shutdown();
	}
}
