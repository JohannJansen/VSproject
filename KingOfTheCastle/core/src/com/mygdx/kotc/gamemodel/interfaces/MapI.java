package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.manager.CombatManager;

public interface MapI {
    void movePlayer(Player player, Vec2d direction) throws TileNotReachableException;
    void spawnPlayer(Player player, Vec2d spawnZoneStart, Vec2d spawnZoneEnd);
    void setPlayerPosOnTile(Vec2d pos, Player player);
    void initiateCombat(Player player1, Player player2, int interactionDistance) throws Exception;

    /**
     * Checks if there's another player in the immediate vicinity of the player
     * @param player
     * @param combatManager
     * @return
     */
    boolean findNearbyPlayers(Player player, CombatManager combatManager);
}
