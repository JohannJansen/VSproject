package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.manager.CombatManager;

public interface MapI {
    /**
     * Moves a Player into the given Direction (North,South, East, West)
     * @param player
     * @param direction
     * @throws TileNotReachableException
     */
    void movePlayer(Player player, Vec2d direction) throws TileNotReachableException;

    /**
     * Spawns a Player on a Map, within the given Spawnzone
     * @param player
     * @param spawnZoneStart marks the bottom Left of the Spawnzone
     * @param spawnZoneEnd markts the Top Right of the Spawnzone
     */
    void spawnPlayer(Player player, Vec2d spawnZoneStart, Vec2d spawnZoneEnd);

    /**
     * Sets a player on a given position
     * @param pos Position, where the Player should spawn
     * @param player the Player to set the Position
     */
    void setPlayerPosOnTile(Vec2d pos, Player player);

    /**
     * Opens up a Combat between two players, when the condition allow it
     * @param player1
     * @param player2
     * @param interactionDistance the distance between the two players
     * @throws Exception
     */
    void initiateCombat(Player player1, Player player2, int interactionDistance) throws Exception;

    /**
     * Checks if there's another player in the immediate vicinity of the player
     *
     * @param player
     * @return
     */
    Player findNearbyPlayers(Player player);
}
