package com.mygdx.kotc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.kotc.KingOfTheCastle;
import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Tile;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.gamemodel.manager.PlayerManager;
import com.mygdx.kotc.inputprocessors.BattleScreenInputProcessor;
import com.mygdx.kotc.inputprocessors.inputevents.ButtonPressEvent;
import com.mygdx.kotc.inputprocessors.inputevents.Event;
import com.mygdx.kotc.viewproxy.PlayerRenderData;
import com.mygdx.kotc.viewproxy.MapRenderData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MapScreen implements Screen, InputProcessor {
    private KingOfTheCastle kingOfTheCastle;
    private MapManager mapManager;
    private Player player;
    private Player player2;
    private List<Player> playerList;
    private PlayerManager playerManager = new PlayerManager();
    private List<MapRenderData> mapRenderDataList;
    private List<PlayerRenderData> playerRenderDataList;
    private OrthographicCamera camera;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private CombatManager combatManager = new CombatManager();



    public MapScreen(KingOfTheCastle kingOfTheCastle) {
        this.kingOfTheCastle = kingOfTheCastle;
        player = PlayerFactory.createTestPlayer();
        player2 = PlayerFactory.createTestPlayer();
        player = kingOfTheCastle.viewProxy.map.getTiles()[7][6].getOccupiedBy();
        player2 = kingOfTheCastle.viewProxy.map.getTiles()[7][5].getOccupiedBy();
        mapManager = new MapManager();
        mapManager.setMap(kingOfTheCastle.viewProxy.map);
        kingOfTheCastle.gameControllerClient.setCurrentScreen(CurrentScreen.MAP);
        Gdx.input.setInputProcessor(new BattleScreenInputProcessor(kingOfTheCastle.gameControllerClient));
    }

    @Override
    public void show() {
//        tileRenderDataList = kingOfTheCastle.viewProxy.mapToTileRenderData();
//        playerRenderDataList = kingOfTheCastle.viewProxy.mapToPlayerRenderData();
        Gdx.graphics.setWindowedMode(1024, 1024);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, kingOfTheCastle.getScreenWidth(), kingOfTheCastle.getScreenHeight());
        kingOfTheCastle.batch.setProjectionMatrix(camera.combined);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderDataList = kingOfTheCastle.viewProxy.mapToMapRenderData();
//        playerRenderDataList = kingOfTheCastle.viewProxy.mapToPlayerRenderData();

//        handleInput(delta);
        update(delta);
        kingOfTheCastle.batch.begin();
        mapRenderDataList.forEach(this::displayTile);
//        playerRenderDataList.forEach(this::displayPlayer);
        kingOfTheCastle.batch.end();
    }

//    public void handleInput(float delta){
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//            Gdx.input.getInputProcessor().keyDown(29);
////            kingOfTheCastle.gameControllerClient.sendInputEvent(new ButtonPressEvent(51));
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//            Gdx.input.getInputProcessor().keyDown(32);
////            right();
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
//            Gdx.input.getInputProcessor().keyDown(51);
////            up();
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
////            down();
//            Gdx.input.getInputProcessor().keyDown(47);
//        }
//    }


    private void update(float delta) {

    }

    public void displayTile(MapRenderData mapRenderData){
        Texture texture = switch (mapRenderData.getTextureType()){
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
                , mapRenderData.getX()*KingOfTheCastle.TEXTUREWIDTH
                , mapRenderData.getY()*KingOfTheCastle.TEXTUREHEIGHT);
        if(mapRenderData.getPlayerTextureType() != null){
            Texture playerTexture = switch (mapRenderData.getPlayerTextureType()) {
                case WIZARD_LEFT -> new Texture(Gdx.files.internal("png/cats/mageCat_cobble_left.png"));
                case WIZARD_RIGHT -> new Texture(Gdx.files.internal("png/cats/mageCat_cobble_right.png"));
                case KNIGHT_LEFT -> new Texture(Gdx.files.internal("png/cats/warriorCat_cobble_left.png"));
                case KNIGHT_RIGHT -> new Texture(Gdx.files.internal("png/cats/warriorCat_cobble_right.png"));
                case WIZARD -> null;
                case KNIGHT -> null;
                case MONK -> null;
                case ARCHER -> null;
                default -> null;
            };
            kingOfTheCastle.batch.draw(playerTexture
                    , mapRenderData.getX()*KingOfTheCastle.TEXTUREWIDTH
                    , mapRenderData.getY()*KingOfTheCastle.TEXTUREHEIGHT);
        }
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

    public void left(){
        try {
            mapManager.movePlayer(player, new Vec2d(-1, 0));
        } catch (TileNotReachableException e) {
            throw new RuntimeException(e);
        }
    }


    public void right() {
        try {
            mapManager.movePlayer(player, new Vec2d(1, 0));
        } catch (TileNotReachableException e) {
            throw new RuntimeException(e);
        }
    }


    public void up() {
        try {
            mapManager.movePlayer(player, new Vec2d(0, 1));
        } catch (TileNotReachableException e) {
            throw new RuntimeException(e);
        }
    }


    public void down() {
        try {
            mapManager.movePlayer(player, new Vec2d(0, -1));
        } catch (TileNotReachableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.B) {
            kingOfTheCastle.setScreen(new BattleScreen(kingOfTheCastle));
            Gdx.app.log("b pressed","b pressed");

            if (mapManager.findNearbyPlayers(player, combatManager)){
                kingOfTheCastle.setScreen(new BattleScreen(kingOfTheCastle));
                Gdx.app.log("b pressed","b pressed");
            }
            return true;
        }
        return false;
    }

    //public Player findNearestPlayer(Player player){
    //   Tile playerTile = new Tile();
    //   playerTile.setPosition(player.getPosition());
    //   Tile tiles [][] = mapManager.getMap().getTiles();
    //   Arrays.stream(tiles).filter()
    //
    //}


    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
