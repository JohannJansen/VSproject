package com.mygdx.kotc.kotcrpc;


public interface RPCIClient {
    /**
     * Marshalls a method call, so it can be sent to the Server
     * @param playerId the player, who requests method invocation
     * @param method the method name as a String
     * @param parameters the Parameters of the method in the correct order
     */
    void call(String playerId, String method, Object[] parameters);

    /**
     * connects to the Server via the given host and port
     * @param host
     * @param port
     */
    void connectToServer(String host, int port);

    /**
     * Client listens for incoming messages from the server
     */
    void listen();
}
