package com.mygdx.kotc.gamecontroller;

import com.mygdx.kotc.applicationstub.ApplicationStubServer;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.exceptions.MaxPlayersReachedException;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.gamemodel.manager.PlayerManager;
import com.mygdx.kotc.gamemodel.repositories.IdGenerator;
import com.mygdx.kotc.kotcrpc.Message;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public  class GameControllerServer implements ControllerOutputI{
    public int MAXPLAYERS = 8;

    private boolean isRunning = true;

    private final long TICKDURATIONMILLIS = 1000;

    private IdGenerator idGenerator = new IdGenerator();

    private Map<Long, Player> playerMapping = new HashMap<>(); //Ids of players

    private MapManager mapManager;

    private CombatManager combatManager;

    private PlayerManager playerManager;

    private GameStateOutput gameStateOutput;

    private ApplicationStubServer applicationStubServer;

    public GameControllerServer(ApplicationStub applicationStub) {
        this.applicationStub = applicationStub;
    }

    //private final ApplicationStub applicationStub = new ApplicationStub();

    public void start(){
        //Setup start map
        mapManager.setMap(MapFactory.createDefaultMap());

        while (!Thread.interrupted()){
            try {
                Thread.sleep(TICKDURATIONMILLIS/2);
                long starttimeModelUpdate = System.currentTimeMillis();
                //launch and join thread for model update

                State state = getServerState();
                applicationStubServer.updateClientGamestates("updateGameState", new Object[]{state});

                long remainingtimeInTick = TICKDURATIONMILLIS/2 - (System.currentTimeMillis() - starttimeModelUpdate);
                Thread.sleep(TICKDURATIONMILLIS);
            } catch (InterruptedException e) {
                System.out.println("Interrupt in Controller Thread");
                throw new RuntimeException(e);
            }
        }
    }

    public void movePlayer(Player player, Vec2d vec2d) throws TileNotReachableException {
        mapManager.movePlayer(player, vec2d);
    }

    public void registerPlayer(Player player) throws MaxPlayersReachedException {
        if(playerMapping.size() >= MAXPLAYERS){
            throw new MaxPlayersReachedException();
        }
        long playerId = idGenerator.newId();
        playerMapping.put(playerId, player);
        player.setPlayerId(playerId);
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

    public State getServerState(){
        return gameStateOutput.getState();
    }

    public Map<String, Player> getPlayerMapping() {
        return playerMapping;
    }

    //    @Override
//    public void run() {
//        applicationStub.hostLobby();
//        while (!Thread.interrupted()){
//            try {
//                Thread.sleep(TICKDURATIONMILLIS/2);
//                long starttimeModelUpdate = System.currentTimeMillis();
//                //launch and join thread for model update
//
//                long remainingtimeInTick = TICKDURATIONMILLIS/2 - (System.currentTimeMillis() - starttimeModelUpdate);
//                Thread.sleep(TICKDURATIONMILLIS);
//            } catch (InterruptedException e) {
//                System.out.println("Interrupt in Controller Thread");
//                throw new RuntimeException(e);
//            }
//
//        }
//
//    }
}
