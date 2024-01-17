package com.mygdx.kotc.viewproxy;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.kotc.KingOfTheCastle;
import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.entities.Tile;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.manager.GameStateOutput;

import java.util.ArrayList;
import java.util.List;

public class ViewProxy implements OutputI{
    GameStateOutput gameStateOutput = new GameStateOutput();
    @Override
    public List<Rectangle> stateToRenderableObject() {
        List<Rectangle> list = new ArrayList<>();
        State state = gameStateOutput.getState();

        for (int x = 0; x < state.getMap().getWidth(); x++){
            for (int y = 0; y < state.getMap().getHeight(); y++){
                Tile tile = state.getMap().getTiles()[y][x];
                //TODO Map design using texture types to split Tiles into lists for different textures
                if (tile.isTraversible()){
                    Rectangle rectangle = new Rectangle();
                    rectangle.width = KingOfTheCastle.TEXTUREWIDTH;
                    rectangle.height = KingOfTheCastle.TEXTUREHEIGHT;
                    rectangle.y = tile.getPosition().getPosY();
                    rectangle.x = tile.getPosition().getPosX();
                    list.add(rectangle);
                }else{
                    Rectangle rectangle = new Rectangle();
                    list.add(rectangle);
                }
            }
        }
        return list;
    }

    public List<TileRenderData> mapToTileRenderData() {
        List<TileRenderData> tileRenderDataList = new ArrayList<>();
        //Map map = gameStateOutput.getMap();
        Map map = MapFactory.createDefaultMap();
        for (int y = 0; y < map.getHeight(); y++){
            for (int x = 0; x < map.getWidth(); x++){
                Tile tile = map.getTiles()[y][x];
                TileRenderData tileRenderData = new TileRenderData(tile.getPosition().getPosX()
                        ,tile.getPosition().getPosY(),tile.getTextureType());
                tileRenderDataList.add(tileRenderData);
            }
        }
        return tileRenderDataList;
    }
}
