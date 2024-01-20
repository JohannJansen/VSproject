package com.mygdx.kotc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.kotc.screens.*;
import com.mygdx.kotc.gamecontroller.GameControllerClient;
import com.mygdx.kotc.screens.BattleScreen;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.viewproxy.TileRenderData;
import com.mygdx.kotc.viewproxy.ViewProxy;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;
import java.util.Random;

public class KingOfTheCastle extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public BitmapFont thiccFont;
	public Stage stage;
	private OrthographicCamera stageCamera;
	private Texture[][] spielfeldTextures;
	private Random random;
	public static final int TEXTUREHEIGHT = 32;
	public static final int TEXTUREWIDTH = 32;
	//private Random random;
	public ViewProxy viewProxy;
	public List<TileRenderData> tileRenderDataList;
	MapScreen mapScreen;
	private int screenWidth = 1024;
	private int screenHeight = 1024;

	public GameControllerClient gameControllerClient;

	@Override
	public void create() {
		gameControllerClient = new GameControllerClient();
		gameControllerClient.start();

		viewProxy = new ViewProxy();
		tileRenderDataList  = viewProxy.mapToTileRenderData();
		Gdx.graphics.setWindowedMode(getScreenWidth(), getScreenHeight());
		batch = new SpriteBatch();
		random = new Random();
		font = new BitmapFont();
		thiccFont = new BitmapFont();
		font.setColor(Color.BLACK);
		thiccFont.setColor(Color.BLACK);
		thiccFont.getData().scale(1.0f);
		//this.setScreen(new BattleScreen(this));
		//this.setScreen(new CharacterSelectScreen(this));
		//this.setScreen(new MapScreen(this));
		//this.setScreen(new StartScreen(this));
		this.setScreen(new ConnectionScreen(this));
	}

	@Override
	public void render() {
		super.render();

		// Render the stage
		//Gdx.gl.glClearColor(0.36f, 0.36f, 0.36f, 1);
//		this.batch.begin();
//		//tileRenderDataList.forEach(mapScreen::displayTile);
//		this.batch.end();
	}

	@Override
	public void dispose () {
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
}
