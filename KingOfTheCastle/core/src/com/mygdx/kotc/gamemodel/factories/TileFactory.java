package com.mygdx.kotc.gamemodel.factories;

import com.mygdx.kotc.gamemodel.entities.TextureType;
import com.mygdx.kotc.gamemodel.entities.Tile;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

import java.util.Random;

public class TileFactory {
    public static Tile createDefaultTile(Vec2d pos){
        Tile tile = new Tile();
        tile.setTraversable(true);
        tile.setPosition(pos);
        return tile;
    }

    public static Tile createUntraversableTile(Vec2d pos){
        Tile tile = new Tile();
        tile.setTraversable(false);
        tile.setPosition(pos);
        return tile;
    }

    public static Tile createTraversableCobbleTile(Vec2d pos){
        Tile tile = new Tile();
        tile.setTraversable(true);
        tile.setPosition(pos);
        TextureType[] possibleTextures = new TextureType[]{TextureType.COBBLE0
                , TextureType.COBBLE1, TextureType.COBBLE2, TextureType.COBBLE3};
        TextureType randomType = possibleTextures[new Random().nextInt(possibleTextures.length)];
        tile.setTextureType(randomType);
        return tile;
    }

    public static Tile createRandomWallTile(Vec2d pos) {
        Tile tile = new Tile();
        tile.setTraversable(false); //bug found
        tile.setPosition(pos);
        TextureType[] possibleTextures = new TextureType[]{TextureType.WALL1, TextureType.WALL2, TextureType.WALL3};
        TextureType randomType = possibleTextures[new Random().nextInt(possibleTextures.length)];
        tile.setTextureType(randomType);
        return tile;
    }

    public static Tile createBarrelObstacleTile(Vec2d pos) {
        Tile tile = new Tile();
        tile.setTraversable(false);  //bug found
        tile.setPosition(pos);
        tile.setTextureType(TextureType.BARREL);
        return tile;
    }
}
