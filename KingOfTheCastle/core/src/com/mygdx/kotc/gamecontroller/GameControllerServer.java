package com.mygdx.kotc.gamecontroller;

import com.mygdx.kotc.applicationstub.ApplicationStubServer;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.exceptions.MaxPlayersReachedException;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.GameStateOutput;
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

    private Map<String, Player> playerMapping = new HashMap<>(); //Ids of players

    private MapManager mapManager;

    private CombatManager combatManager;

    private PlayerManager playerManager;

    private GameStateOutput gameStateOutput;

    private ApplicationStubServer applicationStubServer;

    public GameControllerServer() {
        this.applicationStubServer = new ApplicationStubServer();
        this.mapManager = new MapManager();
        this.combatManager = new CombatManager();
        this.playerManager = new PlayerManager();
        this.gameStateOutput = new GameStateOutput(playerManager, combatManager, mapManager);
    }

    //private final ApplicationStub applicationStub = new ApplicationStub();

    public void run(){
        //Setup start map
        mapManager.setMap(MapFactory.createDefaultMap());

        while (isRunning){
            try {
                Thread.sleep(TICKDURATIONMILLIS/2);
                long starttimeModelUpdate = System.currentTimeMillis();

                //launch and join thread for model update
                applicationStubServer.updateCurrentMove();
                Set<String> keys = applicationStubServer.getCurrentMoveForPlayers().keySet();
                for (String key: keys){
                    Message message = applicationStubServer.getCurrentMoveForPlayers().get(key);
                    Player player = playerMapping.get(key);
                    invokeMethodFromMessage(message, player);
                    applicationStubServer.getCurrentMoveForPlayers().put(key, null);
                }

                State state = getServerState();
                applicationStubServer.updateClientGamestates("updateGameState", new Object[]{state});

                long remainingtimeInTick = TICKDURATIONMILLIS/2 - (System.currentTimeMillis() - starttimeModelUpdate);
                remainingtimeInTick = remainingtimeInTick < 0 ? 0 : remainingtimeInTick;
                Thread.sleep(remainingtimeInTick);
            } catch (InterruptedException e) {
                System.out.println("Interrupt in Controller Thread");
                throw new RuntimeException(e);
            }
        }
    }

    public void invokeMethodFromMessage(Message message, Player player) {
        if (message == null) {
            if (!player.getPlayerInCombat()) {
                return;
            } else {
                //TODO add default for attack
                return;
            }
        }

        String methodname = message.getMethodname();
        Object[] parameters =  message.getParameters();

        if (methodname.equals("movePlayer")){
            try {
                mapManager.movePlayer((Player) parameters[0], (Vec2d) parameters[1]);
            } catch (TileNotReachableException e) {
                System.out.println(new StringBuilder()
                        .append("Tile not reachable player with ID:").append(" ")
                        .append(((Player) parameters[0]).getPlayerId()).append("skips turn"));
            }
        } else if (methodname.equals("registerPlayer")) {
            try {
                String playerId = (String) parameters[0];
                registerPlayer(playerId);
                mapManager.spawnPlayer(playerMapping.get(playerId), mapManager.getMap().SPAWNZONEBOTLEFT, mapManager.getMap().SPAWNZONETOPRIGHT );
            } catch (MaxPlayersReachedException e) {
                System.out.println("Max players reached");
            }
        } else {
            System.out.println("no method by that name");
        }
    }

    public void movePlayer(Player player, Vec2d vec2d) throws TileNotReachableException {
        mapManager.movePlayer(player, vec2d);
    }

    public String registerPlayer(String playerId) throws MaxPlayersReachedException {
        if(playerMapping.size() >= MAXPLAYERS){
            throw new MaxPlayersReachedException();
        }
        Player player = PlayerFactory.createWizard();
        playerMapping.put(playerId, player);
        player.setPlayerId(playerId);
        playerManager.getPlayerList().add(player);
        return playerId;
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
