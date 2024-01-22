package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.kotcrpc.ClientStub;
import com.mygdx.kotc.kotcrpc.Message;

public class ApplicationStubClient implements ApplicationStubIClient{
    private final ClientStub clientStub;

    public ApplicationStubClient() {
        clientStub = new ClientStub();
    }


    public void callServerMethod(String playerId, String method, Object[] parameters) { //client
        clientStub.call(playerId, method, parameters);
    }

    public Message receiveMessage() { //ran on client side
       return clientStub.getMessage();
    }


    public void joinServer(String playerId){
        clientStub.connectToServer("DESKTOP-NCFTCH6", 8898);
        clientStub.call(playerId, "registerPlayer", new Object[]{playerId});
    }

    public ClientStub getClientStub() {
        return clientStub;
    }
}
