package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

public interface MapI {
    void movePlayer(Player player, Vec2d initialPos, Vec2d direction);
    void spawnPlayer(Player player, Map map, Vec2d spawnZoneStart, Vec2d spawnZoneEnd);
    void setPlayerPos(Vec2d pos, Player player, Map map);
    void initiateCombat(Vec2d positionP1, Vec2d positionP2, int interactionDistance);
}
