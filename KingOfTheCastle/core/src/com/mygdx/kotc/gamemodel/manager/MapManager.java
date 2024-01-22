package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.*;
import com.mygdx.kotc.gamemodel.exceptions.CombatNotInitiatableException;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.interfaces.MapI;


public class MapManager implements MapI{

    private final CombatManager combatManager;

    public Map map;

    public MapManager(CombatManager combatManager) {
        this.combatManager = combatManager;
        this.map = MapFactory.createDefaultMap();
    }

    @Override
    public void movePlayer(Player player, Vec2d direction) throws TileNotReachableException{
        Vec2d newPos = new Vec2d(player.getPosition().getPosX() + direction.getPosX(), player.getPosition().getPosY() + direction.getPosY());
        Tile tile = map.getTiles()[newPos.getPosX()][newPos.getPosY()];

        if(!player.getPlayerInCombat() && tile.isTraversable()){
            removePlayerFromCurrentTile(player);
            setPlayerPosOnTile(newPos, player);
        }else {
            System.out.println("This Tile is an obstacle and isn't reachable!");
            throw new TileNotReachableException();
        }
    }

    @Override
    public void spawnPlayer(Player player, Vec2d spawnZoneStart, Vec2d spawnZoneEnd) {
        for(int i = spawnZoneStart.getPosY(); i <= spawnZoneEnd.getPosY(); i++){
            for(int j = spawnZoneStart.getPosX(); j <= spawnZoneEnd.getPosX(); j++){
                Tile tile = map.getTiles()[j][i];
                if(!tile.isTraversable()){
                    continue;
                }

                if (!tile.isOccupied()){
                    Vec2d playerSpawnPos = new Vec2d(j, i);
                    setPlayerPosOnTile(playerSpawnPos, player);
                    return;
                }
            }
        }
    }

    @Override
    public void setPlayerPosOnTile(Vec2d pos, Player player) {
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
            combatManager.createCombat(player1,player2);
            //test
            System.out.println("Combat between " + player1 +
                    "and " + player2 + " initiated!!!");
        }else{
            throw new CombatNotInitiatableException();
        }
    }

    private void removePlayerFromCurrentTile(Player player){
        map.getTiles()[player.getPosition().getPosX()][player.getPosition().getPosY()].setOccupiedBy(null);
        map.getTiles()[player.getPosition().getPosX()][player.getPosition().getPosY()].setTraversable(true);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public Player findNearbyPlayers(Player player){

        Tile tiles [][] = getMap().getTiles();
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                // Skip the center tile
                if (dx == 0 && dy == 0) continue;

                int x = player.getPosition().getPosX() + dx;
                int y = player.getPosition().getPosY() + dy;

                // Check bounds
                if (x < 0 || x >= tiles.length || y < 0 || y >= tiles[0].length) continue;

                // Check if tile is occupied by a player
                Player occupyingPlayer = tiles[x][y].getOccupiedBy();
                if (occupyingPlayer != null && !occupyingPlayer.getPlayerInCombat()) {
                    return occupyingPlayer;
                }
            }
        }
        return null;
    }



}



