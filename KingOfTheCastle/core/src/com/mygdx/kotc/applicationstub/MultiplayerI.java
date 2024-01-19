package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.kotcrpc.Message;

public interface MultiplayerI {
    void callServerControllerMethod(String method, Object[] parameters);
    void receiveGameState(String method, Object[] parameters);
    Message getCallForPlayer(int playerID);
    void sendGameState(State state);
    void hostLobby();
    void joinLobby(int lobbyID);
}
