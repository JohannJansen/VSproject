package com.mygdx.kotc.kotcrpc;

import com.badlogic.gdx.utils.Json;
import com.mygdx.kotc.applicationstub.ApplicationStub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;


public class ServerSkeleton implements RPCIServer{

    private ServerSocket serverSocket;
    private ApplicationStub applicationStub;
    public ServerSkeleton(ApplicationStub applicationStub) {
        this.applicationStub = applicationStub;
    }

    public ServerSkeleton() {
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("Server started on port 8888");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public Message listenForIncommingCalls() {
//        try {
//            Socket clientSocket = serversocket.accept();
//            String receivedJson;
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
//                receivedJson = reader.readLine();
//                System.out.println("Received JSON from server: " + receivedJson);
//            }
//            Message message = marshallFromJson(receivedJson);
//            System.out.println(message.getMethodname());
//            System.out.println(Arrays.toString(message.getParameters()));
//            clientSocket.close();
//        } catch (IOException e) {
//            System.out.println("Error when connecting to Server");
//            throw new RuntimeException(e);
//        }
//        //TODO
//        return null;
//    }

    @Override
    public void listenForIncomingCalls() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                // Handle each client in a separate thread
                new Thread(() -> handleClient(clientSocket)).start();
            } catch (IOException e) {
                System.out.println("Error when connecting to Server");
                throw new RuntimeException(e);
            }
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String receivedJson = reader.readLine();
            System.out.println("Received JSON from client: " + receivedJson);

            Message message = marshallFromJson(receivedJson);
            String methodname = message.getMethodname();
            Object[] parameters = message.getParameters();
            System.out.println(message.getMethodname());
            System.out.println(Arrays.toString(message.getParameters()));
            applicationStub.callServerControllerMethod(methodname, parameters);

            // TODO: Implement your logic to process the message and send a response if needed

            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error handling client");
            e.printStackTrace();
        }
    }

    private Message marshallFromJson(String jsonString){
        Json json = new Json();
        return json.fromJson(Message.class, jsonString);
    }


    public void getOwnHostName() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostname = localHost.getHostName();
        System.out.println("Hostname: " + hostname);
    }

    public static void main(String[] args) {
        ServerSkeleton serverSkeleton = new ServerSkeleton();
        serverSkeleton.listenForIncomingCalls();
    }
}
