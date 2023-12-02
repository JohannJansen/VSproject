package com.mygdx.kotc.gamemodel.entities;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
    private Vec2d position;
    private boolean traversable;
    private Player occupiedBy;

    public Vec2d getPosition() {
        return position;
    }

    public void setPosition(Vec2d position) {
        this.position = position;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public Player getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(Player occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
}
