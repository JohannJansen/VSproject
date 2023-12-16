package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.gamemodel.entities.State;

public interface MultiplayerI {
    void callServerControllerMethod(String method, Object[] parameters);
    State receiveGameState();
    void waitForMethodCall();
    void sendGameState(State state);
    void hostLobby();
    void joinLobby(int lobbyID);
}
