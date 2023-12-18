package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;

public interface MapI {
    void movePlayer(Player player, Vec2d direction) throws TileNotReachableException;
    void spawnPlayer(Player player, Vec2d spawnZoneStart, Vec2d spawnZoneEnd);
    void setPlayerPos(Vec2d pos, Player player);
    void initiateCombat(Player player1, Player player2, int interactionDistance) throws Exception;
}
