package com.mygdx.kotc.viewproxy;

import com.mygdx.kotc.gamemodel.entities.PlayerTextureType;
import com.mygdx.kotc.gamemodel.entities.TextureType;

//TODO: implement
public class PlayerRenderData {

    private int x;
    private int y;
    PlayerTextureType playerTextureType;

    public PlayerRenderData(int x, int y, PlayerTextureType playerTextureType) {
        this.x = x;
        this.y = y;
        this.playerTextureType = playerTextureType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PlayerTextureType getPlayerTextureType() {
        return playerTextureType;
    }
}
