package com.mygdx.kotc.applicationstub;

public interface ApplicationStubIServer {

    /**
     * receives a message from the client with his current Move
     */
    void updateCurrentMove();

    /**
     * sends a message to the client with the current Gamestate
     * @param method the methodname as a String
     * @param parameters the Parameters of the method in the correct order
     */
    void updateClientGamestates(String method, Object[] parameters);
}
