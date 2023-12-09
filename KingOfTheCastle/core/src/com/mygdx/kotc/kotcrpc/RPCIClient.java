package com.mygdx.kotc.kotcrpc;


public interface RPCIClient {
    void invoke(String method, Object[] parameters);
    void connectToServer(String host, int port);
}
