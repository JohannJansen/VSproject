package com.mygdx.kotc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.kotc.screens.StartScreen;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.screens.MapScreen;
import com.mygdx.kotc.viewproxy.ViewProxy;

import java.util.Random;

public class KingOfTheCastle extends Game {
	public SpriteBatch batch;
	public static final int TEXTUREHEIGHT = 16;
	public static final int TEXTUREWIDTH = 16;
	private Texture[][] spielfeldTextures;
	private final int spielfeldBreite = 32;
	private final int spielfeldHoehe = 32;
	private Random random;
	public ViewProxy viewProxy;

	MapManager mapManager;
	private int screenWidth = 512;
	private int screenHeight = 512;

	private Texture getRandomCobblestoneTexture() {
		// Zufällige Auswahl einer Cobblestone-Texture
		int randomIndex = random.nextInt(3); // Anzahl der verschiedenen Texturen
		return new Texture(Gdx.files.internal("png/cobble/cobble_" + randomIndex + ".png"));
	}

	private Texture getRandomWallTexture() {
		// Zufällige Auswahl einer Cobblestone-Texture
		int randomIndex = random.nextInt(2); // Anzahl der verschiedenen Texturen
		return new Texture(Gdx.files.internal("png/walls/wall_" + randomIndex + ".png"));
	}


	@Override
	public void create() {
		//this.setScreen(new MapScreen(this));
		Gdx.graphics.setWindowedMode(512, 512);
		viewProxy = new ViewProxy();
		batch = new SpriteBatch();
		random = new Random();
		this.setScreen(new StartScreen(this));
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClearColor(0.36f, 0.36f, 0.36f, 1);

	}

	@Override
	public void dispose () {
		batch.dispose();

		// Ressourcen freigeben
		for (int x = 0; x < spielfeldBreite; x++) {
			for (int y = 0; y < spielfeldHoehe; y++) {
				spielfeldTextures[x][y].dispose();
			}
		}
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}
}
