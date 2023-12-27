package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.kotcrpc.Message;

import java.util.HashMap;
import java.util.Map;

public class ApplicationStub implements MultiplayerI{
    private Map<Long, Message> currentMoveForPlayer = new HashMap<>();

    @Override
    public void callServerControllerMethod(String method, Object[] parameters) {

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
}
