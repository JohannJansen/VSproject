package com.mygdx.kotc.gamecontroller;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.repositories.IdGenerator;

import java.sql.Timestamp;
import java.util.List;

public class GameControllerServer implements ControllerOutputI, InputI{
    public int MAXPLAYERS = 8;
    private boolean isRunning = true;
    private final long TICKDURATIONMILLIS = 1000;
    private IdGenerator idGenerator = new IdGenerator();

    public void start(){
        while (isRunning){
            Timer tickTimer = new Timer();
        }
    }

    private void mapInput(){

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
