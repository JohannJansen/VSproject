package com.mygdx.kotc.gamemodel.factories;

import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Tile;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

public class MapFactory {
    private static final int DEFAULTHEIGHT = 32;
    private static final int DEFAULTWIDTH = 32;

    public static Map createTestMap(int width, int height){
        Map map = new Map(height, width);
        Tile[][] tiles = map.getTiles();
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

    public static Map createDefaultMap(){
        Map map = new Map(DEFAULTHEIGHT, DEFAULTWIDTH);
        Tile[][] tiles = map.getTiles();
        //Create Ground
        for (int x = 0; x < DEFAULTWIDTH; x++) {
            for (int y = 0; y < DEFAULTHEIGHT; y++) {
                tiles[x][y] = TileFactory.createTraversableCobbleTile(new Vec2d(x,y)); //Bug [y][x] found
            }
        }
        //Create Walls
        for (int x = 0; x < DEFAULTWIDTH; x++) {
            tiles[0][x] = TileFactory.createRandomWallTile(new Vec2d(x,0));
        }
        for (int x = 0; x < DEFAULTWIDTH; x++) {
            tiles[DEFAULTHEIGHT-1][x] = TileFactory.createRandomWallTile(new Vec2d(x,DEFAULTHEIGHT-1));
        }
        for (int y = 0; y < DEFAULTHEIGHT; y++) {
            tiles[y][0] = TileFactory.createRandomWallTile(new Vec2d(0,y));
        }
        for (int y = 0; y < DEFAULTWIDTH; y++) {
            tiles[y][DEFAULTWIDTH-1] = TileFactory.createRandomWallTile(new Vec2d(DEFAULTWIDTH-1,y));
        }

        //Create obstacles
        tiles[7][5] = TileFactory.createBarrelObstacleTile(new Vec2d(7,5));
        return map;
    }
}
