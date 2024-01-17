package com.mygdx.kotc.gamecontroller;

import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.exceptions.MaxPlayersReachedException;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.repositories.IdGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class GameControllerServer implements ControllerOutputI, InputI{
    public int MAXPLAYERS = 8;
    private boolean isRunning = true;
    private final long TICKDURATIONMILLIS = 1000;
    private IdGenerator idGenerator = new IdGenerator();
    private Map<Long, Player> playerMapping = new HashMap<Long, Player>();

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

    private void mapInput(){

    }

    private void registerPlayer() throws MaxPlayersReachedException {
        if(playerMapping.size() >= MAXPLAYERS){
            throw new MaxPlayersReachedException();
        }
        long playerId = idGenerator.newId();
        playerMapping.put(playerId, null);
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

    @Override
    public void left() throws TileNotReachableException {

    }

    @Override
    public void right() {

    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }
}
