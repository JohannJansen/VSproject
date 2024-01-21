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
import com.mygdx.kotc.inputprocessors.BattleScreenInputProcessor;
import com.mygdx.kotc.inputprocessors.inputevents.ButtonPressEvent;
import com.mygdx.kotc.inputprocessors.inputevents.Event;
import com.mygdx.kotc.viewproxy.PlayerRenderData;
import com.mygdx.kotc.viewproxy.MapRenderData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MapScreen implements Screen {
    private KingOfTheCastle kingOfTheCastle;
    private MapManager mapManager;
    private Player player;
    private Player player2;
    private List<Player> playerList;
    private List<MapRenderData> mapRenderDataList;
    private List<PlayerRenderData> playerRenderDataList;
    private OrthographicCamera camera;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private CombatManager combatManager = new CombatManager();



    public MapScreen(KingOfTheCastle kingOfTheCastle) {
        this.kingOfTheCastle = kingOfTheCastle;
        kingOfTheCastle.gameControllerClient.setCurrentScreen(CurrentScreen.MAP);
        Gdx.input.setInputProcessor(new BattleScreenInputProcessor(kingOfTheCastle.gameControllerClient));
    }

    @Override
    public void show() {
        Gdx.graphics.setWindowedMode(1024, 1024);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderDataList = kingOfTheCastle.viewProxy.mapToMapRenderData();
        update(delta);
        kingOfTheCastle.batch.begin();
        mapRenderDataList.forEach(this::displayTile);
        kingOfTheCastle.batch.end();
    }


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
                case WIZARD -> new Texture(Gdx.files.internal("png/cats/mageCat_cobble_right.png"));
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
}
