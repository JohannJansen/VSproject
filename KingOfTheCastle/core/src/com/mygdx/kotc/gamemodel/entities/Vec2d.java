package com.mygdx.kotc.gamemodel.entities;

import java.util.Objects;

public class Vec2d {
    private int posX;
    private int posY;

    public Vec2d(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public static double calculateDistance(Vec2d pos1, Vec2d pos2) {
        int x1 = pos1.getPosX();
        int y1 = pos1.getPosY();
        int x2 = pos2.getPosX();
        int y2 = pos2.getPosY();

        int deltaX = x2 - x1;
        int deltaY = y2 - y1;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2d vec2d = (Vec2d) o;
        return posX == vec2d.posX && posY == vec2d.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
