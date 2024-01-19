package com.mygdx.kotc.kotcrpc;
import com.badlogic.gdx.utils.Json;
import com.mygdx.kotc.applicationstub.ApplicationStub;
import com.mygdx.kotc.gamecontroller.GameControllerClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class ClientStub implements RPCIClient {
    private Socket socket;
    private ApplicationStub applicationStub;

    public ClientStub(ApplicationStub applicationStub) {
        this.applicationStub = applicationStub;
    }

    @Override
    public void invoke(String method, Object[] parameters) {

        Message message = new Message(method, parameters);
        String messageJson = marshallToJson(message);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
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
        while (!socket.isConnected()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error while waiting for socket to connect: " + e.getMessage());
            }
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (true) {
                String receivedGameState = reader.readLine();
                if (receivedGameState == null) {
                    break; // Server disconnected
                }
                Message message = unmarshallFromJson(receivedGameState);
                String methodname = message.getMethodname();
                Object[] parameters = message.getParameters();
                applicationStub.receiveGameState(methodname, parameters);

            }
        } catch (IOException e) {
            System.out.println("Error listening for messages from server: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public static void main(String[] args) {
//        ClientStub clientStub = new ClientStub();
//        clientStub.connectToServer("localhost",8888);
//        clientStub.invoke("johann", new Object[]{"test"});
//    }
}
