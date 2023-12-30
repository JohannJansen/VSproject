package com.mygdx.kotc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.kotc.gamemodel.manager.MapManager;

import java.util.Random;

public class KingOfTheCastle extends Game {
	public SpriteBatch batch;
	public static final int TEXTUREHEIGHT = 16;
	public static final int TEXTUREWIDTH = 16;
	private Random random;
	public ViewProxy viewProxy;

	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(512, 512);
		mapManager = new MapManager();
		batch = new SpriteBatch();
		random = new Random();
		// Initialisiere das Spielfeld mit zufälligen Cobblestone-Texturen
		spielfeldTextures = new Texture[spielfeldBreite][spielfeldHoehe];
		for (int x = 0; x < spielfeldBreite; x++) {
			for (int y = 0; y < spielfeldHoehe; y++) {
				spielfeldTextures[x][y] = getRandomCobblestoneTexture();
			}
		}
		// Setze die Ränder des Spielfelds auf Mauer unten
		for (int i = 0; i < 32; i++) {
			spielfeldTextures[i][0] = getRandomWallTexture();
		}
		// Setze die Ränder des Spielfelds auf Mauer links
		for (int i = 0; i < 32; i++) {
			spielfeldTextures[0][i] = getRandomWallTexture();
		}

		// Setze die Ränder des Spielfelds auf Mauer rechts
		for (int i = 0; i < 32; i++) {
			spielfeldTextures[31][i] = getRandomWallTexture();
		}

		// Setze die Ränder des Spielfelds auf Mauer oben
		for (int i = 0; i < 32; i++) {
			spielfeldTextures[i][31] = getRandomWallTexture();
		}

		// Platzieren von untraversableThings
		for (int i = 0; i < 60; i++) {
			int x = 1 + random.nextInt(30);
			int y = 1 + random.nextInt(30);
			spielfeldTextures[x][y] = new Texture(Gdx.files.internal("png/untraversableThings/untraversable_" + random.nextInt(2) + ".png"));
			mapManager.getMap().getTiles()[y][x].setTraversable(false);
		}
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClearColor(0.36f, 0.36f, 0.36f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
}
