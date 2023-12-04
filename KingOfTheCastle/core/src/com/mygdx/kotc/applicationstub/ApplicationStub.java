package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.gamemodel.entities.State;

public class ApplicationStub implements MultiplayerI{
    @Override
    public void callServerControllerMethod(String method, Object[] parameters) {

    }

    @Override
    public State receiveGameState() {
        return null;
    }

    @Override
    public void executeCalledMethod(String method, Object[] parameters) {

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
