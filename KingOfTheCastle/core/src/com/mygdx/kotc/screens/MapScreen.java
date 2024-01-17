package com.mygdx.kotc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.kotc.KingOfTheCastle;
import com.mygdx.kotc.gamecontroller.InputI;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.gamemodel.manager.PlayerManager;
import com.mygdx.kotc.viewproxy.TileRenderData;

import java.util.List;

public class MapScreen implements Screen, InputI{
    private KingOfTheCastle kingOfTheCastle;
    private MapManager mapManager = new MapManager();
    private Player player;
    private List<Player> playerList;

    public MapScreen(KingOfTheCastle kingOfTheCastle) {
        this.kingOfTheCastle = kingOfTheCastle;
        player = PlayerFactory.createTestPlayer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        update(delta);
        kingOfTheCastle.batch.begin();
        List<TileRenderData> tileRenderDataList = kingOfTheCastle.viewProxy.mapToTileRenderData();
        tileRenderDataList.forEach(this::displayTile);
        kingOfTheCastle.batch.end();
    }

    public void handleInput(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            left();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            right();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            up();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            down();
        }
    }


    private void update(float delta) {

    }

    public void displayTile(TileRenderData tileRenderData){
        Texture texture = switch (tileRenderData.getTextureType()){
            case WALL1 -> new Texture(Gdx.files.internal("png/walls/wall_0.png"));
            case WALL2 -> new Texture(Gdx.files.internal("png/walls/wall_1.png"));
            case WALL3 -> new Texture(Gdx.files.internal("png/walls/wall_2.png"));
            case BARREL -> new Texture(Gdx.files.internal("png/untraversableThings/untraversable_0.png"));
            case TREE -> new Texture(Gdx.files.internal("png/untraversableThings/untraversable_1.png"));
            //case GRASS -> new Texture(Gdx.files.internal("png/"));
            case DOOR -> new Texture(Gdx.files.internal("png/doors/door_1"));
            case DOOROPEN -> new Texture(Gdx.files.internal("png/doors/door_2"));
            case COBBLE0 -> new Texture(Gdx.files.internal("png/cobble/cobble_1_new.png"));
            case COBBLE1 -> new Texture(Gdx.files.internal("png/cobble/cobble_2_new.png"));
            case COBBLE2 -> new Texture(Gdx.files.internal("png/cobble/cobble_3_new.png"));
            case COBBLE3 -> new Texture(Gdx.files.internal("png/cobble/cobble_4_new.png"));
            default -> new Texture(Gdx.files.internal("png/cobble/cobble_1.png"));
        };
        kingOfTheCastle.batch.draw(texture
                , tileRenderData.getX()*KingOfTheCastle.TEXTUREWIDTH
                , tileRenderData.getY()*KingOfTheCastle.TEXTUREHEIGHT);
    }

    public void displayPlayer(PlayerRenderData playerRenderData){

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


    // InputI methods
    @Override
    public void left(){
        try {
            int posX = player.getPosition().getPosX();
            int posY = player.getPosition().getPosY();
            mapManager.movePlayer(player, new Vec2d(posX-1, posY));

        } catch (TileNotReachableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void right() {
        try {
            int posX = player.getPosition().getPosX();
            int posY = player.getPosition().getPosY();
            mapManager.movePlayer(player, new Vec2d(posX+1, posY));
        } catch (TileNotReachableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void up() {
        try {
            int posX = player.getPosition().getPosX();
            int posY = player.getPosition().getPosY();
            mapManager.movePlayer(player, new Vec2d(posX, posY+1));
        } catch (TileNotReachableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void down() {
        try {
            int posX = player.getPosition().getPosX();
            int posY = player.getPosition().getPosY();
            mapManager.movePlayer(player, new Vec2d(posX, posY-1));
        } catch (TileNotReachableException e) {
            throw new RuntimeException(e);
        }
    }
}
