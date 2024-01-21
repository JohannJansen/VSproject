package com.mygdx.kotc.kotcrpc;


public interface RPCIClient {
    void call(String playerId, String method, Object[] parameters);
    void connectToServer(String host, int port);
}
