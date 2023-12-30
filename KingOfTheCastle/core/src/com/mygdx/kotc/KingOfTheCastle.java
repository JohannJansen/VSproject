package com.mygdx.kotc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.kotc.screens.MapScreen;
import com.mygdx.kotc.viewproxy.ViewProxy;

import java.util.Random;

public class KingOfTheCastle extends Game {
	public SpriteBatch batch;
	public static final int TEXTUREHEIGHT = 16;
	public static final int TEXTUREWIDTH = 16;
	private Random random;
	public ViewProxy viewProxy;

	@Override
	public void create() {
		this.setScreen(new MapScreen(this));
		Gdx.graphics.setWindowedMode(512, 512);
		viewProxy = new ViewProxy();
		batch = new SpriteBatch();
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
	}
}
