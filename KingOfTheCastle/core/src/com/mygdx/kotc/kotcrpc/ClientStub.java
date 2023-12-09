package com.mygdx.kotc.kotcrpc;
import com.badlogic.gdx.utils.Json;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientStub implements RPCIClient {
    Socket serverSocket;

    @Override
    public void invoke(String method, Object[] parameters) {

        Message message = new Message(method, parameters);
        String messageJson = marshallToJson(message);

        try {
            OutputStreamWriter output = new OutputStreamWriter(serverSocket.getOutputStream());
            output.write(messageJson);
        } catch (Exception e){
            System.out.println("Problem getting outputStream");
        }
    }

    public void connectToServer(String host, int port){
        try {
            serverSocket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("Error when trying to connect Client to server");
        }
    }

    private String marshallToJson(Message message){
        Json json = new Json();
        return json.toJson(message);
    }
}
