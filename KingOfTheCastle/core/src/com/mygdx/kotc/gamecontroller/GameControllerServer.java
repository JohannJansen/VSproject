package com.mygdx.kotc.gamecontroller;

import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.repositories.IdGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GameControllerServer implements ControllerOutputI, InputI{
    public int MAXPLAYERS = 8;
    private boolean isRunning = true;
    private final long TICKDURATIONMILLIS = 1000;
    private IdGenerator idGenerator = new IdGenerator();
    private ArrayList<Player> players = new ArrayList<>(8);

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
