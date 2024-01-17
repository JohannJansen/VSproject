package com.mygdx.kotc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.kotc.screens.MapScreen;
import com.mygdx.kotc.viewproxy.TileRenderData;
import com.mygdx.kotc.viewproxy.ViewProxy;

import java.util.List;
import java.util.Random;

public class KingOfTheCastle extends Game {
	public SpriteBatch batch;
	public static final int TEXTUREHEIGHT = 32;
	public static final int TEXTUREWIDTH = 32;
	//private Random random;
	public ViewProxy viewProxy;
	public List<TileRenderData> tileRenderDataList;
	MapScreen mapScreen = new MapScreen(this);

	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(1024, 1024);
		viewProxy = new ViewProxy();
		batch = new SpriteBatch();
		this.setScreen(mapScreen);
		tileRenderDataList  = viewProxy.mapToTileRenderData();
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClearColor(0.36f, 0.36f, 0.36f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.begin();
		tileRenderDataList.forEach(mapScreen::displayTile);
		this.batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
