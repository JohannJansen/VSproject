package com.mygdx.kotc.kotcrpc;

import com.badlogic.gdx.utils.Json;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

import java.io.IOException;
import java.io.InputStream;
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
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int read;
            String output = "";
            while((read = inputStream.read(buffer)) != -1) {
                output = new String(buffer, 0, read);
                System.out.print(output);
                System.out.flush();
            }
            Message message = marshallFromJson(output);
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
