package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.kotcrpc.Message;

public interface ApplicationStubIClient {
    /**
     * calls a method on the server
     * @param playerId the player, who requests method invocation
     * @param method the method name as a String
     * @param parameters the Parameters of the method in the correct order
     */
    void callServerMethod(String playerId, String method, Object[] parameters);

    /**
     * receives a message from the server
     * @return the message
     */
    Message receiveMessage();

    /**
     * connects to the server
     * @param playerId the playerID of the player, who wants to join
     */
    void joinServer(String playerId);
}
