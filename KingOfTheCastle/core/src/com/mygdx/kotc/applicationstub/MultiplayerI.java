package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.gamemodel.entities.State;

public interface MultiplayerI {
    void callServerControllerMethod(String method, String... parameters);
    State receiveGameState();
    void executeCalledMethod(String method, String... parameters);
    void sendGameState(State state);
    void hostLobby();
    void joinLobby(int lobbyID);
}
