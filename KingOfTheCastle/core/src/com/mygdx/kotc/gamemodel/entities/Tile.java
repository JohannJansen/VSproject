package com.mygdx.kotc.gamemodel.entities;

import java.io.Serializable;

public class Tile {
    private Vec2d position;
    private boolean traversable;
    private Player occupiedBy;
    private TextureType textureType;

    public Tile(){

    }

    public Vec2d getPosition() {
        return position;
    }

    public void setPosition(Vec2d position) {
        this.position = position;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public void setTraversable(boolean traversible) {
        this.traversable = traversible;
    }

    public Player getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(Player occupiedBy) {
        this.occupiedBy = occupiedBy;
        traversable = false;
    }

    public boolean isOccupied(){
        return occupiedBy != null;
    }
    public void setTextureType(TextureType textureType){ this.textureType = textureType; }

    public TextureType getTextureType(){return textureType;}

}
