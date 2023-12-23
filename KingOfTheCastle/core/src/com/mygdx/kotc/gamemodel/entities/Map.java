package com.mygdx.kotc.gamemodel.entities;

public class Map {

    private Tile[][] map;
    
    private int height;
    private int width;

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile[][] getMap() {
        return map;
    }
    public void setMap(Tile[][] map) {
        this.map = map;
    }
}
