package com.mygdx.kotc.kotcrpc;

import com.badlogic.gdx.utils.Json;
import com.mygdx.kotc.applicationstub.ApplicationStub;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerSkeleton implements RPCIServer{

    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private CopyOnWriteArrayList<Socket> connectedClients;
    private ApplicationStub applicationStub;
    public ServerSkeleton(ApplicationStub applicationStub) {
        this.applicationStub = applicationStub;
        try {
            serverSocket = new ServerSocket(8888);
            executorService = Executors.newCachedThreadPool();
            connectedClients = new CopyOnWriteArrayList<>();
            System.out.println("Server started on port 8888");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ServerSkeleton() {
        try {
            serverSocket = new ServerSocket(8888);
            executorService = Executors.newCachedThreadPool();
            connectedClients = new CopyOnWriteArrayList<>();
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
//                new Thread(() -> handleClient(clientSocket)).start();
                connectedClients.add(clientSocket);
                executorService.submit(() -> handleClient(clientSocket));
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

            Message message = unmarshallFromJson(receivedJson);
            assert message != null;
            String methodname = message.getMethodname();
            Object[] parameters = message.getParameters();
            System.out.println(message.getMethodname());
            System.out.println(Arrays.toString(message.getParameters()));
            applicationStub.callServerControllerMethod(methodname, parameters);

            // TODO: Implement your logic to process the message and send a response if needed

        } catch (IOException e) {
            System.out.println("Error handling client");
            e.printStackTrace();
        }
    }

    public void sendToAllClients(Message message) {
        while (connectedClients.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error while waiting for socket to connect: " + e.getMessage());
            }
        }
        for (Socket clientSocket : connectedClients) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                writer.write(marshallToJson(message));
                writer.flush();
                System.out.println("Game state sent to clients");
            } catch (IOException e) {
                System.out.println("Error sending game state to client");
            }
        }

    }

    private Message unmarshallFromJson(String jsonString){
        Json json = new Json();
        return json.fromJson(Message.class, jsonString);
    }

    private String marshallToJson(Message message){
        Json json = new Json();
        return json.toJson(message);
    }


    public void getOwnHostName() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostname = localHost.getHostName();
        System.out.println("Hostname: " + hostname);
    }

//    public static void main(String[] args) {
//        ServerSkeleton serverSkeleton = new ServerSkeleton(new ApplicationStub());
//        serverSkeleton.listenForIncomingCalls();
//    }
}
