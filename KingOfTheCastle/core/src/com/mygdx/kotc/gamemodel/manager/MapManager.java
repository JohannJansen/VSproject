package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.entities.Tile;
import com.mygdx.kotc.gamemodel.exceptions.CombatNotInitiatableException;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.interfaces.MapI;

import java.util.Random;


public class MapManager implements MapI{
    int DEFAULTMAPWIDTH = 32;
    int DEFAULTMAPHEIGHT = 32;

    Map map = MapFactory.createTestMap(DEFAULTMAPWIDTH, DEFAULTMAPHEIGHT);

    /**
     * creates a new Map with the given height and widht
     * @param width
     * @param height
     */
    public void createMap(int width, int height){
        Map map = MapFactory.createTestMap(width,height);
        this.map = map;
    }

    /**
     * pre: destination is reachable, player !inCombat
     * post: the position of the Player is changed to the new direction
     * and
     * @param player the player that wants to move
     * @param direction the player is heading to.
     */
    @Override
    public void movePlayer(Player player, Vec2d direction) throws TileNotReachableException{
        Vec2d newPos = new Vec2d(player.getPosition().getPosX() + direction.getPosX(), player.getPosition().getPosY() + direction.getPosY());
        Tile tile = map.getTiles()[newPos.getPosX()][newPos.getPosY()];

        if(!player.getPlayerInCombat() && tile.isTraversible()){
            removePlayerFromCurrentTile(player);
            player.setPosition(newPos);
            setPlayerPos(newPos, player);
        }else {
            throw new TileNotReachableException();
        }
    }
    /**
     * Randomly spawns the player in a Spawn area
     * @param player the player to s
     * @param spawnZoneStart Start of the spawnzone
     * @param spawnZoneEnd End of the spawnzone
     */
    @Override
    public void spawnPlayer(Player player, Vec2d spawnZoneStart, Vec2d spawnZoneEnd) {
        for(int i = spawnZoneStart.getPosY(); i <= spawnZoneEnd.getPosY(); i++){
            for(int j = spawnZoneStart.getPosX(); j <= spawnZoneEnd.getPosX(); j++){
                Tile tile = map.getTiles()[j][i];
                if(!tile.isTraversible()){
                    continue;
                }
                Random random = new Random();
                int randomNum = random.nextInt(2)+1;
                if (randomNum == 2){
                    Vec2d playerSpawnPos = new Vec2d(j,i);
                    setPlayerPos(playerSpawnPos,player);
                }
            }
        }
    }

    @Override
    public void setPlayerPos(Vec2d pos, Player player) {
        player.setPosition(pos);
        Tile tile = map.getTiles()[pos.getPosX()][pos.getPosY()];
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

    private void removePlayerFromCurrentTile(Player player){
        map.getTiles()[player.getPosition().getPosX()][player.getPosition().getPosY()].setOccupiedBy(null);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
