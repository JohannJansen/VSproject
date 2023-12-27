package com.mygdx.kotc.viewproxy;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.entities.Tile;
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
                    rectangle.height = 20;
                    rectangle.width = 20;
                    list.add(rectangle);
                }else{
                    Rectangle rectangle = new Rectangle();
                    rectangle.x = 0; //x koordinate des Tiles
                    rectangle.y = 23; //y koordinate des Tiles
                    rectangle.height = 20;
                    rectangle.width = 20;
                    list.add(rectangle);
                }
            }
        }
        return list;
    }
}
