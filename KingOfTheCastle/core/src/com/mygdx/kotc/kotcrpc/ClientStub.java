package com.mygdx.kotc.kotcrpc;
import com.badlogic.gdx.utils.Json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

public class ClientStub implements RPCIClient {
    Socket socket;

    @Override
    public void invoke(String method, Object[] parameters) {

        Message message = new Message(method, parameters);
        String messageJson = marshallToJson(message);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
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
        }
    }

    private String marshallToJson(Message message){
        Json json = new Json();
        return json.toJson(message);
    }

    public static void main(String[] args) {
        ClientStub clientStub = new ClientStub();
        clientStub.connectToServer("localhost",8888);
        clientStub.invoke("johann", new Object[]{"test"});
    }
}
