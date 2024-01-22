package com.mygdx.kotc.kotcrpc;


public interface RPCIClient {
    void call(String playerId, String method, Object[] parameters);

    /**
     * connects to the Server via the given host and port
     * @param host
     * @param port
     */
    void connectToServer(String host, int port);
}
