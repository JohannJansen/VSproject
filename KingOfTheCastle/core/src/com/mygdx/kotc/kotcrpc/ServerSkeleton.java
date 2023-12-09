package com.mygdx.kotc.kotcrpc;

import com.badlogic.gdx.utils.Json;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerSkeleton implements RPCIServer{
    ServerSocket serversocket;
    List<Socket> registeredClients = new ArrayList<>();

    public ServerSkeleton() {
        try {
            serversocket = new ServerSocket(8888);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void call(String method, Object[] parameters) {

    }

    private Message marshallFromJson(String jsonString){
        Json json = new Json();
        return json.fromJson(Message.class, jsonString);
    }
}
