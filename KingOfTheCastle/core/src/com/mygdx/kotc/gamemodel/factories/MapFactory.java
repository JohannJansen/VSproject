package com.mygdx.kotc.gamemodel.factories;

import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Tile;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

public class MapFactory {

    public static Map createMap(int width, int height){
        Map map = new Map();
        Tile[][] tiles = new Tile[width][height];
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Vec2d v2d = new Vec2d(i, j);
                if (v2d.getPosX() == 8 && v2d.getPosY() == 8){
                    Tile tile = TileFactory.createUntraversableTile(v2d);
                    tiles[i][j] = tile;
                }
                else {
                    Tile tile = TileFactory.createDefaultTile(v2d);
                    tiles[i][j] = tile;
                }
            }
        }
        map.setMap(tiles);

        return map;
    }
}
