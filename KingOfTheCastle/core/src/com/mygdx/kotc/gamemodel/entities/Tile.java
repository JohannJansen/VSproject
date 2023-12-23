package com.mygdx.kotc.gamemodel.entities;

public class Tile {
    private Vec2d position;
    private boolean traversible;
    private Player occupiedBy;
    private TextureType textureType;

    public Vec2d getPosition() {
        return position;
    }

    public void setPosition(Vec2d position) {
        this.position = position;
    }

    public boolean isTraversible() {
        return traversible;
    }

    public void setTraversable(boolean traversible) {
        this.traversible = traversible;
    }

    public Player getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(Player occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public void setTextureType(TextureType textureType){ this.textureType = textureType; }

    public TextureType getTextureType(){return textureType;}

}
