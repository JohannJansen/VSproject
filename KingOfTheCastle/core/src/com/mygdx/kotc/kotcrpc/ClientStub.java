package com.mygdx.kotc.kotcrpc;
import com.badlogic.gdx.utils.Json;
import com.mygdx.kotc.applicationstub.ApplicationStubClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientStub implements RPCIClient {
    private Socket socket;
    private Message message;

    public ClientStub() {

    }

    @Override
    public void call(String playerId, String method, Object[] parameters) {

        Message message = new Message(playerId, method, parameters);
        String messageJson = marshallToJson(message);
        messageJson += '\n';

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            assert messageJson != null;
            writer.write(messageJson);
            writer.flush();
            System.out.println(messageJson + " written to server");
        } catch (Exception e){
            System.out.println("Problem getting outputStream");
        }
    }

    public void connectToServer(String host, int port){
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("Error when trying to connect Client to server");
            e.printStackTrace();
        }
    }

    public void connectToServer(InetAddress address, int port){
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            System.out.println("Error when trying to connect Client to server");
        }
    }

    private String marshallToJson(Message message){
        Json json = new Json();
        return json.toJson(message);
    }

    private Message unmarshallFromJson(String jsonString){
        Json json = new Json();
        return json.fromJson(Message.class, jsonString);
    }

    public void startListening() {
        while (socket.isClosed()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error while waiting for socket to connect: " + e.getMessage());
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            char[] buffer = new char[1024]; // Assuming 1 kilobyte size
            int bytesRead;
            while (!Thread.interrupted()) {
                StringBuilder receivedGameState = new StringBuilder();
                while (!receivedGameState.toString().endsWith("\n")) {
                    // Append the read characters to the receivedGameState StringBuilder
                    bytesRead = reader.read(buffer);
                    receivedGameState.append(buffer, 0, bytesRead);
                }
                String gameState = receivedGameState.toString().trim();
                Message message = unmarshallFromJson(gameState);
                assert message != null;
                this.message = message;
            }
        } catch (IOException e) {
            System.out.println("Error listening for messages from server: " + e.getMessage());
        }
    }
    public Message getMessage() {
        return message;
    }
}
