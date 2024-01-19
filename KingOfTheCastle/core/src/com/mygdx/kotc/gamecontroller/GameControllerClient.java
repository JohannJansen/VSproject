package com.mygdx.kotc.gamecontroller;

import com.badlogic.gdx.Input;
import com.mygdx.kotc.applicationstub.ApplicationStub;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.GameStateOutput;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.gamemodel.manager.PlayerManager;
import com.mygdx.kotc.inputprocessors.inputevents.ButtonPressEvent;
import com.mygdx.kotc.inputprocessors.inputevents.MouseClickEvent;
import com.mygdx.kotc.inputprocessors.inputevents.Event;
import com.mygdx.kotc.kotcrpc.Status;
import com.mygdx.kotc.screens.CurrentScreen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameControllerClient implements InputI{
    private int playerID;

    private ApplicationStub applicationStub;

    private CurrentScreen currentScreen;

    private CombatManager combatManager;

    private MapManager mapManager;

    private PlayerManager playerManager;

    private Player player;

    public void start(){
        //applicationStub.invokeServerMethod("registerPlayer", new Object[]{});
        applicationStub = new ApplicationStub(Status.CLIENT);
        applicationStub.joinServer(player);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> applicationStub.getClientStub().startListening());
        executorService.shutdown();
        applicationStub.setGameControllerClient(this);
        playerManager = new PlayerManager();
        combatManager = new CombatManager();
        mapManager = new MapManager();
    }

    public void updateGameState(State state){ //method to call from ApplicationStub
        mapManager.setMap(state.getMap());
        combatManager.setActiveCombats(state.getCombatList());
        playerManager.setPlayerList(state.getPlayerList());
    }


    @Override
    public void sendInputEvent(Event event) {
        if(event instanceof MouseClickEvent){
            handleMouseInput((MouseClickEvent) event);
        } else if (event instanceof ButtonPressEvent){
            handleButtonEvent((ButtonPressEvent) event);
        } else {
            throw new IllegalArgumentException("Event did not have a valid type");
        }
    }

    public void handleMouseInput(MouseClickEvent mouseClickEvent){

    }

    public void handleButtonEvent(ButtonPressEvent buttonPressEvent){
        if (currentScreen == CurrentScreen.MAP){
            handleButtonMap(buttonPressEvent);
        } else if (currentScreen == CurrentScreen.BATTLE){
            handleButtonBattle(buttonPressEvent);
        }
    }

    private void handleButtonMap(ButtonPressEvent buttonPressEvent){
        if (buttonPressEvent.keycode == Input.Keys.W) {
            applicationStub.invokeServerMethod("movePlayer", new Object[]{playerManager.getPlayerById(playerID), new Vec2d(0,1)});
        }
        if (buttonPressEvent.keycode == Input.Keys.A) {
            applicationStub.invokeServerMethod("movePlayer", new Object[]{playerManager.getPlayerById(playerID), new Vec2d(-1,0)});
        }
        if (buttonPressEvent.keycode == Input.Keys.S) {
            applicationStub.invokeServerMethod("movePlayer", new Object[]{playerManager.getPlayerById(playerID), new Vec2d(0,-1)});
        }
        if (buttonPressEvent.keycode == Input.Keys.D) {
            applicationStub.invokeServerMethod("movePlayer", new Object[]{playerManager.getPlayerById(playerID), new Vec2d(1,0)});
        }
    }

    private void handleButtonBattle(ButtonPressEvent buttonPressEvent){

    }

    public void setCurrentScreen(CurrentScreen currentScreen) {
        this.currentScreen = currentScreen;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
