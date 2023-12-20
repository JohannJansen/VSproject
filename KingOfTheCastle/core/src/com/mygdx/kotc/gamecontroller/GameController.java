package com.mygdx.kotc.gamecontroller;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.kotc.gamemodel.entities.Player;

import java.sql.Timestamp;
import java.util.List;

public class GameController implements ControllerOutputI, InputI{
    public int MAXPLAYERS = 8;
    private boolean isRunning = true;
    private final long TICKDURATIONMILLIS = 1000;

    public void start(){
        while (isRunning){
            Timer tickTimer = new Timer();
        }
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
