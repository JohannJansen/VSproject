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

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mygdx.kotc.utils.ConfigReader.readConfig;

public class KingOfTheCastle extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public BitmapFont thiccFont;
    public static final int TEXTUREHEIGHT = 32;
    public static final int TEXTUREWIDTH = 32;
    public ViewProxy viewProxy;
    private int screenWidth = 1024;
    private int screenHeight = 1024;

    public GameControllerClient gameControllerClient;

    @Override
    public void create() {
        startClient();

        viewProxy = gameControllerClient.getViewProxy();
        Gdx.graphics.setWindowedMode(getScreenWidth(), getScreenHeight());
        batch = new SpriteBatch();
        font = new BitmapFont();
        thiccFont = new BitmapFont();
        font.setColor(Color.BLACK);
        thiccFont.setColor(Color.BLACK);
        thiccFont.getData().scale(1.0f);
        this.setScreen(new StartScreen(this));
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
        String filePath = "Configs/config.cfg";
        Properties config = readConfig(filePath);
        String serverHostname = config.getProperty("serverHostname");

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		gameControllerClient = new GameControllerClient(serverHostname);
		executorService.submit(gameControllerClient::run);
		executorService.shutdown();
	}
}
