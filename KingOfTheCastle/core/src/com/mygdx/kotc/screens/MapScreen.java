package com.mygdx.kotc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.kotc.KingOfTheCastle;
import com.mygdx.kotc.viewproxy.TileRenderData;

import java.util.List;

public class MapScreen implements Screen {
    private final KingOfTheCastle kingOfTheCastle;

    public MapScreen(KingOfTheCastle kingOfTheCastle) {
        this.kingOfTheCastle = kingOfTheCastle;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        kingOfTheCastle.batch.begin();
        List<TileRenderData> tileRenderDataList = kingOfTheCastle.viewProxy.mapToTileRenderData();
        tileRenderDataList.forEach(this::displayTile);
        kingOfTheCastle.batch.end();
    }

    private void displayTile(TileRenderData tileRenderData){
        Texture texture = switch (tileRenderData.getTextureType()){
            case WALL1 -> new Texture(Gdx.files.internal("png/walls/wall_0.png"));
            case WALL2 -> new Texture(Gdx.files.internal("png/walls/wall_1.png"));
            case WALL3 -> new Texture(Gdx.files.internal("png/walls/wall_2.png"));
            case BARREL -> new Texture(Gdx.files.internal("png/untraversableThings/untraversable_0.png"));
            case TREE -> new Texture(Gdx.files.internal("png/untraversableThings/untraversable_1.png"));
            case GRASS -> new Texture(Gdx.files.internal("png/"));
            case DOOR -> new Texture(Gdx.files.internal("png/doors/door_1"));
            case DOOROPEN -> new Texture(Gdx.files.internal("png/doors/door_2"));
            case COBBLE0 -> new Texture(Gdx.files.internal("png/cobble/cobble_0.png"));
            case COBBLE1 -> new Texture(Gdx.files.internal("png/cobble/cobble_1.png"));
            case COBBLE2 -> new Texture(Gdx.files.internal("png/cobble/cobble_2.png"));
            case COBBLE3 -> new Texture(Gdx.files.internal("png/cobble/cobble_3.png"));
            default -> new Texture(Gdx.files.internal("png/cobble/cobble_0.png"));
        };
        kingOfTheCastle.batch.draw(texture
                , tileRenderData.getX()*KingOfTheCastle.TEXTUREWIDTH
                , tileRenderData.getY()*KingOfTheCastle.TEXTUREHEIGHT);
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
