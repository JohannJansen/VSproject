package com.mygdx.kotc.viewproxy;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.kotc.KingOfTheCastle;
import com.mygdx.kotc.gamemodel.entities.*;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.manager.GameStateOutput;

import java.util.ArrayList;
import java.util.List;

public class ViewProxy implements OutputI{
    private GameStateOutput gameStateOutput;

    public ViewProxy(GameStateOutput gameStateOutput) {
        this.gameStateOutput = gameStateOutput;
    }

    @Override
    public List<Rectangle> stateToRenderableObject() {
        List<Rectangle> list = new ArrayList<>();
        State state = gameStateOutput.getState();

        for (int x = 0; x < state.getMap().getWidth(); x++){
            for (int y = 0; y < state.getMap().getHeight(); y++){
                Tile tile = state.getMap().getTiles()[x][y];
                //TODO Map design using texture types to split Tiles into lists for different textures
                if (tile.isTraversable()){
                    Rectangle rectangle = new Rectangle();
                    rectangle.width = KingOfTheCastle.TEXTUREWIDTH;
                    rectangle.height = KingOfTheCastle.TEXTUREHEIGHT;
                    rectangle.x = tile.getPosition().getPosX();
                    rectangle.y = tile.getPosition().getPosY();
                    list.add(rectangle);
                }else{
                    Rectangle rectangle = new Rectangle();
                    list.add(rectangle);
                }
            }
        }
        return list;
    }

    public List<MapRenderData> mapToMapRenderData() {
        Map map = gameStateOutput.getMap();
        List<MapRenderData> mapRenderDataList = new ArrayList<>();
        for (int y = 0; y < map.getHeight(); y++){
            for (int x = 0; x < map.getWidth(); x++){
                Tile tile = map.getTiles()[y][x];
                MapRenderData mapRenderData = new MapRenderData(tile.getPosition().getPosX()
                        ,tile.getPosition().getPosY(),tile.getTextureType());
                if(tile.isOccupied()){
                    Player player = tile.getOccupiedBy();
                    mapRenderData.setPlayerTextureType(player.getPlayerTextureType());
                }
                mapRenderDataList.add(mapRenderData);
            }
        }
        return mapRenderDataList;
    }

    @Override
    public List<CombatRenderData> combatToCombatRenderData() {
        return null;
    }
}
