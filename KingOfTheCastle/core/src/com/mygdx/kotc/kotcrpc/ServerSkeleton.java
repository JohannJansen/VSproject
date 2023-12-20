package com.mygdx.kotc.kotcrpc;

import com.badlogic.gdx.utils.Json;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


public class ServerSkeleton implements RPCIServer{
    ServerSocket serversocket;

    public ServerSkeleton() {
        try {
            serversocket = new ServerSocket(8888);
            System.out.println("Server started on port 8888");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message listenForIncommingCalls() {
        try {
            Socket clientSocket = serversocket.accept();
            String receivedJson;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                receivedJson = reader.readLine();
                System.out.println("Received JSON from server: " + receivedJson);
            }
            Message message = marshallFromJson(receivedJson);
            System.out.println(message.getMethodname());
            System.out.println(Arrays.toString(message.getParameters()));
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error when connecting to Server");
            throw new RuntimeException(e);
        }
        //TODO
        return null;
    }

    private Message marshallFromJson(String jsonString){
        Json json = new Json();
        return json.fromJson(Message.class, jsonString);
    }

    public static void main(String[] args) {
        ServerSkeleton serverSkeleton = new ServerSkeleton();
        serverSkeleton.listenForIncommingCalls();
    }
}
