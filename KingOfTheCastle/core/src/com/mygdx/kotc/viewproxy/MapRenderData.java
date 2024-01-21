package com.mygdx.kotc.viewproxy;

import com.mygdx.kotc.gamemodel.entities.PlayerTextureType;
import com.mygdx.kotc.gamemodel.entities.TextureType;

public class MapRenderData {
    private int x;
    private int y;
    private TextureType textureType;
    private PlayerTextureType playerTextureType;

    public MapRenderData(int x, int y, TextureType textureType) {
        this.x = x;
        this.y = y;
        this.textureType = textureType;
        playerTextureType = null;
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

    public void setPlayerTextureType(PlayerTextureType playerTextureType) {
        this.playerTextureType = playerTextureType;
    }

    public PlayerTextureType getPlayerTextureType() {
        return playerTextureType;
    }
}
