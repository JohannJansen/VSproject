package com.mygdx.kotc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class KingOfTheCastle extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture[][] spielfeldTextures;
	private final int spielfeldBreite = 40;
	private final int spielfeldHoehe = 40;
	private Random random;

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
		batch = new SpriteBatch();
		random = new Random();
		// Initialisiere das Spielfeld mit zufälligen Cobblestone-Texturen
		spielfeldTextures = new Texture[spielfeldBreite][spielfeldHoehe];
		for (int x = 0; x < spielfeldBreite; x++) {
			for (int y = 0; y < spielfeldHoehe; y++) {
				spielfeldTextures[x][y] = getRandomCobblestoneTexture();
			}
		}
		for (int i = 0; i < 40; i++) {
			spielfeldTextures[i][0] = getRandomWallTexture();
		}
		for (int i = 1; i < 38; i++) {
			spielfeldTextures[0][i] = getRandomWallTexture();
		}

		for (int i = 1; i < 38; i++) {
			spielfeldTextures[39][i] = getRandomWallTexture();
		}

		for (int i = 0; i < 40; i++) {
			spielfeldTextures[i][29] = getRandomWallTexture();
		}

		for (int i = 0; i < 60; i++) {
			spielfeldTextures[1 + random.nextInt(38)][1 + random.nextInt(28)] = new Texture(Gdx.files.internal("png/untraversableThings/untraversable_" + random.nextInt(2) + ".png"));
		}


	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.36f, 0.36f, 0.36f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		// Schleife für die Breite
		for (int x = 0; x < spielfeldBreite; x++) {
			// Schleife für die Höhe
			for (int y = 0; y < spielfeldHoehe; y++) {
				// Position des aktuellen Cobblestone-Bildes basierend auf Zellenposition und Bildgröße
				float positionX = x * spielfeldTextures[x][y].getWidth();
				float positionY = y * spielfeldTextures[x][y].getHeight();

				batch.draw(spielfeldTextures[x][y], positionX, positionY);
			}
		}
		batch.end();
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
