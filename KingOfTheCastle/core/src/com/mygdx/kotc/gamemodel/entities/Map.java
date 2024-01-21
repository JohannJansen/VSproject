package com.mygdx.kotc.gamemodel.entities;

import java.io.Serializable;

public class Map {

    private Tile[][] tiles;
    
    private int height;
    private int width;

    public final Vec2d SPAWNZONEBOTLEFT = new Vec2d(10,2);
    public final Vec2d SPAWNZONETOPRIGHT = new Vec2d(20,10);

    public Map() {

    }

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.tiles = new Tile[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setMap(Tile[][] map) {
        this.tiles = map;
    }
}
