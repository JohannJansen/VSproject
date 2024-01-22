package com.mygdx.kotc.kotcrpc;

public interface RPCIServer {
    void listenForIncomingCalls();

    /**
     * sends a message to all clients which previously opened a connection with the server through listenForIncomingCalls
     * @param message the message send to the clients
     */
    void sendToAllClients(Message message);
}
