package com.mygdx.kotc.gamecontroller;

import com.mygdx.kotc.applicationstub.ApplicationStub;
import com.mygdx.kotc.applicationstub.MultiplayerI;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.exceptions.MaxPlayersReachedException;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.gamemodel.manager.PlayerManager;
import com.mygdx.kotc.gamemodel.repositories.IdGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class GameControllerServer implements ControllerOutputI{
    public int MAXPLAYERS = 8;

    private boolean isRunning = true;

    private final long TICKDURATIONMILLIS = 1000;

    private IdGenerator idGenerator = new IdGenerator();

    private Map<Long, Player> playerMapping = new HashMap<>(); //Ids of players

    private MapManager mapManager = new MapManager();

    private CombatManager combatManager = new CombatManager();

    private PlayerManager playerManager = new PlayerManager();


    //private final ApplicationStub applicationStub = new ApplicationStub();

    public void start(){
        while (isRunning){
            try {
                Thread.sleep(TICKDURATIONMILLIS/2);
                long starttimeModelUpdate = System.currentTimeMillis();
                //launch and join thread for model update
                long remainingtimeInTick = TICKDURATIONMILLIS/2 - (System.currentTimeMillis() - starttimeModelUpdate);
                Thread.sleep(TICKDURATIONMILLIS);
            } catch (InterruptedException e) {
                System.out.println("Interrupt in Controller Thread");
                throw new RuntimeException(e);
            }

        }
    }

    private void movePlayer(Player player, Vec2d vec2d) throws TileNotReachableException {
        mapManager.movePlayer(player, vec2d);
    }

    private void registerPlayer(Player player) throws MaxPlayersReachedException {
        if(playerMapping.size() >= MAXPLAYERS){
            throw new MaxPlayersReachedException();
        }
        long playerId = idGenerator.newId();
        playerMapping.put(playerId, player);
//        player.setPlayerId(playerId);
        playerManager.getPlayerList().add(player);
    }

    @Override
    public Timestamp getTime() {
        return null;
    }

    @Override
    public Timestamp getRemainingTime() {
        return null;
    }

    @Override
    public List<Player> getLobbyPlayerList() {
        return null;
    }
}
