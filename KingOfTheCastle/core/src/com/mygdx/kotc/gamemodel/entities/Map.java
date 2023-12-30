package com.mygdx.kotc.gamemodel.entities;

public class Map {

    private Tile[][] tiles;
    
    private int height;
    private int width;

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
