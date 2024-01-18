package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.gamecontroller.GameControllerServer;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.kotcrpc.ClientStub;
import com.mygdx.kotc.kotcrpc.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ApplicationStub implements MultiplayerI{
    private Map<Long, Message> currentMoveForPlayer = new HashMap<>();
    private final GameControllerServer gameControllerServer = new GameControllerServer();
//    private final GameControllerClient gameControllerClient = new GameControllerClient();
    ClientStub clientStub = new ClientStub();

    @Override
    public void callServerControllerMethod(String method, Object[] parameters) {
        Class<?> controllerClass = gameControllerServer.getClass();
        try {
            Method serverMethod = controllerClass.getMethod(method, Object[].class);
            serverMethod.invoke(gameControllerServer, (Object) parameters);
        } catch (NoSuchMethodException | IllegalAccessException |InvocationTargetException e) {
            System.out.println("Error invoking method on server controller");
            throw new RuntimeException(e);
        }
    }

    public void invokeServerMethod(String method, Object[] parameters) {
        clientStub.invoke(method, parameters);


    }

    @Override
    public State receiveGameState() {
        return null;
    }

    @Override
    public Message getCallForPlayer(int playerID) {
        return null;
    }

    @Override
    public void sendGameState(State state) {

    }

    @Override
    public void hostLobby() {

    }

    @Override
    public void joinLobby(int lobbyID) {

    }

    public void joinServer(Player player){
        clientStub.connectToServer("DESKTOP-3UNJBSN", 8888);
        clientStub.invoke("registerPlayer", new Object[]{player});

    }
}
