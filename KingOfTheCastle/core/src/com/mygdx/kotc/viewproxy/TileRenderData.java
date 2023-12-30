package com.mygdx.kotc.viewproxy;

import com.mygdx.kotc.gamemodel.entities.TextureType;

public class TileRenderData {
    private int x;
    private int y;
    private TextureType textureType;

    public TileRenderData(int x, int y, TextureType textureType) {
        this.x = x;
        this.y = y;
        this.textureType = textureType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TextureType getTextureType() {
        return textureType;
    }
}
