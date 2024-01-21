package com.mygdx.kotc.gamecontroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.kotc.applicationstub.ApplicationStubClient;
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
import com.mygdx.kotc.kotcrpc.Message;
import com.mygdx.kotc.screens.BattleScreen;
import com.mygdx.kotc.screens.CurrentScreen;
import com.mygdx.kotc.viewproxy.ViewProxy;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameControllerClient implements InputI{
    private String playerID;

    private boolean isRunning;

    private ApplicationStubClient applicationStubClient;

    private CurrentScreen currentScreen;

    private CombatManager combatManager;

    private MapManager mapManager;

    private PlayerManager playerManager;

    private GameStateOutput gameStateOutput;

    private ViewProxy viewProxy;

    private Player player;

    public GameControllerClient() {
        this.applicationStubClient = new ApplicationStubClient();
        this.mapManager = new MapManager();
        this.combatManager = new CombatManager();
        this.playerManager = new PlayerManager();
        this.gameStateOutput = new GameStateOutput(playerManager, combatManager, mapManager);
        this.viewProxy = new ViewProxy(gameStateOutput);
        isRunning = true;
        UUID uuid = UUID.randomUUID();
        playerID = uuid.toString();
    }

    public void run(){
        //applicationStub.invokeServerMethod("registerPlayer", new Object[]{});
        applicationStubClient.joinServer(playerID);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> applicationStubClient.getClientStub().startListening());

        while (isRunning){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //TODO notify
            Message message = applicationStubClient.receiveMessage();
            if (message != null){
                State state = (State) message.getParameters()[0];
                if (state != null) {
                    updateGameState(state);
                    System.out.println("gamestate updated");
                    for (Player player: playerManager.getPlayerList()){
                        System.out.println("player: " + player.getPlayerId() + " has position: "
                                + player.getPosition().getPosX()+player.getPosition().getPosY());
                    }
                }
            }
        }
        executorService.shutdown();
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
        } else {
            throw new IllegalArgumentException("Current screen was not valid");
        }
    }

    private void handleButtonMap(ButtonPressEvent buttonPressEvent){
        if (buttonPressEvent.keycode == Input.Keys.W) {
            applicationStubClient.callServerMethod(playerID, "movePlayer", new Object[]{playerID, new Vec2d(0,1)});
        }
        if (buttonPressEvent.keycode == Input.Keys.A) {
            applicationStubClient.callServerMethod(playerID, "movePlayer", new Object[]{playerID, new Vec2d(-1,0)});
        }
        if (buttonPressEvent.keycode == Input.Keys.S) {
            applicationStubClient.callServerMethod(playerID, "movePlayer", new Object[]{playerID, new Vec2d(0,-1)});
        }
        if (buttonPressEvent.keycode == Input.Keys.D) {
            applicationStubClient.callServerMethod(playerID, "movePlayer", new Object[]{playerID, new Vec2d(1,0)});
        }
        if(buttonPressEvent.keycode == Input.Keys.B){
            applicationStubClient.callServerMethod(playerID,"findNearbyPlayers", new Object[]{playerID,combatManager});
            currentScreen = CurrentScreen.BATTLE;
            System.out.println(currentScreen);
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

    public ViewProxy getViewProxy() {
        return viewProxy;
    }
}
