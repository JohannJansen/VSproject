package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.entities.Tile;
import com.mygdx.kotc.gamemodel.exceptions.CombatNotInitiatableException;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.interfaces.MapI;

import java.util.Random;


public class MapManager implements MapI{
    int DEFAULTMAPWIDTH = 32;
    int DEFAULTMAPHEIGHT = 32;

    Map map = MapFactory.createMap(DEFAULTMAPWIDTH, DEFAULTMAPHEIGHT);
    /**
     * pre: destination is reachable, player !inCombat
     * post: the position of the Player is changed to the new direction
     * and
     * @param player the player that wants to move
     * @param direction the player is heading to.
     */
    @Override
    public void movePlayer(Player player, Vec2d direction) {

        Tile tile = map.getMap()[direction.getPosX()][direction.getPosY()];
        if(!player.getPlayerInCombat() && tile.isTraversable()){
            player.setPosition(direction);
            setPlayerPos(direction,player,map);
        }else {
            throw new RuntimeException("The destination is not reachable! Please choose a different one");
        }
    }
    /**
     * Randomly spawns the player in a Spawn area
     * @param player the player to spawn
     * @param map the map the player gets spawed in
     * @param spawnZoneStart Start of the spawnzone
     * @param spawnZoneEnd End of the spawnzone
     */
    @Override
    public void spawnPlayer(Player player, Map map, Vec2d spawnZoneStart, Vec2d spawnZoneEnd) {
        for(int i = spawnZoneStart.getPosY(); i <= spawnZoneEnd.getPosY(); i++){
            for(int j = spawnZoneStart.getPosX(); j <= spawnZoneEnd.getPosX(); j++){
                Tile tile = map.getMap()[j][i];
                if(!tile.isTraversable()){
                    continue;
                }
                Random random = new Random();
                int randomNum = random.nextInt(5)+1;
                if (randomNum == 3){
                    Vec2d playerSpawnPos = new Vec2d(j,i);
                    setPlayerPos(playerSpawnPos,player,map);
                }
            }
        }
    }

    @Override
    public void setPlayerPos(Vec2d pos, Player player, Map map) {
        player.setPosition(pos);
        Tile tile = map.getMap()[pos.getPosX()][pos.getPosY()];
        tile.setOccupiedBy(player);
    }

    @Override
    public void initiateCombat(Player player1, Player player2, int interactionDistance) throws CombatNotInitiatableException {
        if(!player1.getPlayerInCombat() && !player2.getPlayerInCombat()
                && Vec2d.calculateDistance(player1.getPosition(), player2.getPosition()) <= interactionDistance){
            player1.setPlayerInCombat(true);
            player2.setPlayerInCombat(true);
        }else{
            throw new CombatNotInitiatableException();
        }
    }
}
