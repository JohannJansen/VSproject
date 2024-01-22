package com.mygdx.kotc.gamecontroller;

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
import com.mygdx.kotc.inputprocessors.inputevents.Event;
import com.mygdx.kotc.inputprocessors.inputevents.MouseClickEvent;
import com.mygdx.kotc.kotcrpc.Message;
import com.mygdx.kotc.screens.CurrentScreen;
import com.mygdx.kotc.viewproxy.ViewProxy;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameControllerClient implements InputI{

    private final String hostname;

    private String playerID;

    private boolean isRunning;

    private final ApplicationStubClient applicationStubClient;

    private CurrentScreen currentScreen;

    private final CombatManager combatManager;

    private final MapManager mapManager;

    private final PlayerManager playerManager;

    private final GameStateOutput gameStateOutput;

    private final ViewProxy viewProxy;

    private Player player;

    public GameControllerClient(String hostname) {
        this.hostname = hostname;
        this.applicationStubClient = new ApplicationStubClient(hostname);
        this.combatManager = new CombatManager();
        this.mapManager = new MapManager(combatManager);
        this.playerManager = new PlayerManager();
        this.gameStateOutput = new GameStateOutput(playerManager, combatManager, mapManager);
        this.viewProxy = new ViewProxy(gameStateOutput);
        isRunning = true;
        UUID uuid = UUID.randomUUID();
        playerID = uuid.toString();
    }

    public void run(){
        applicationStubClient.joinServer(playerID);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> applicationStubClient.getClientStub().listen());

        while (isRunning){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Set the player object for the client only if the server has created it
            if (player == null){
                if(playerManager.getPlayerById(playerID)!=null){
                    player = playerManager.getPlayerById(playerID);
                }
            }
            if (playerManager.getPlayerById(playerID) != null && playerManager.getPlayerById(playerID).getPlayerInCombat()) {
                currentScreen = CurrentScreen.BATTLE;
            }


            Message message = applicationStubClient.receiveMessage();
            if (message != null){
                State state = (State) message.getParameters()[0];
                if (state != null) {
                    updateGameState(state);
                    System.out.println("Gamestate updated");
                }
            }
        }
        executorService.shutdown();
    }

    public void updateGameState(State state){
        mapManager.setMap(state.getMap());
        combatManager.setActiveCombats(state.getCombatList());
        playerManager.setPlayerList(state.getPlayerList());
    }

    @Override
    public boolean sendInputEvent(Event event) {
        if(event instanceof MouseClickEvent){
            handleMouseInput((MouseClickEvent) event);
            return true;
        } else if (event instanceof ButtonPressEvent){
            handleButtonEvent((ButtonPressEvent) event);
            return true;
        } else {
            throw new IllegalArgumentException("Event did not have a valid type");
        }
    }

    private void handleMouseInput(MouseClickEvent mouseClickEvent){

    }

    private void handleButtonEvent(ButtonPressEvent buttonPressEvent){
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
            applicationStubClient.callServerMethod(playerID,"initiateCombat", new Object[]{player,
                    mapManager.findNearbyPlayers(player), 1});
            //test
            System.out.println("Initiate combat sent for: " + player + "and " + mapManager.findNearbyPlayers(player));
            if(mapManager.findNearbyPlayers(player) != null){
                currentScreen = CurrentScreen.BATTLE;
            }
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
