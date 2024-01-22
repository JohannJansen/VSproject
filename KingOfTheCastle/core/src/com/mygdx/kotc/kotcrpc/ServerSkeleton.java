package com.mygdx.kotc.kotcrpc;

import com.badlogic.gdx.utils.Json;

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

    private final ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final CopyOnWriteArrayList<Socket> connectedClients;
    private final CopyOnWriteArrayList<Message> messageQueue;


    public ServerSkeleton() {
        messageQueue = new CopyOnWriteArrayList<>();
        try {
            serverSocket = new ServerSocket(8898);
            executorService = Executors.newCachedThreadPool();
            connectedClients = new CopyOnWriteArrayList<>();
            System.out.println("Server started on port 8898 with hostname: " + getOwnHostName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void listenForIncomingCalls() {
        while (!Thread.interrupted()) {
            try {
                Socket clientSocket = serverSocket.accept();
                clientSocket.setKeepAlive(true);
                connectedClients.add(clientSocket);
                executorService.submit(() -> handleClient(clientSocket));
            } catch (IOException e) {
                System.out.println("Error when accepting Connection to Server");
                throw new RuntimeException(e);
            }
        }
    }

    private void handleClient(Socket clientSocket) {
        while (!Thread.interrupted()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String receivedJson = reader.readLine();
                System.out.println("Received JSON from client: " + receivedJson);

                //System.out.println("test");
                Message message = unmarshallFromJson(receivedJson);

                messageQueue.add(message);

                System.out.println("Method name received: " + message.getMethodname());
                System.out.println("Paramters received: " + Arrays.toString(message.getParameters()));
//                System.out.println("CLIENT SOCKET IS CONNECTED: " + clientSocket.isConnected());
            } catch (IOException e) {
                System.out.println("Error handling client");
                e.printStackTrace();
            }
        }
    }

    @Override
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
            if(!clientSocket.isClosed()) {
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    String marshalledMessage = marshallToJson(message);
                    marshalledMessage += '\n';
//                    System.out.println(marshalledMessage.endsWith("\n"));

                    int kilobyteSize = 1024;

                    for (int i = 0; i < marshalledMessage.length(); i += kilobyteSize) {
                        int end = Math.min(i + kilobyteSize, marshalledMessage.length());
                        String packet = marshalledMessage.substring(i, end);

                        // Write the packet to the buffer
                        writer.write(packet);
                        writer.flush();
                    }
                    writer.flush();
                } catch (IOException e) {
                    System.out.println("Error sending game state to client");
                    e.printStackTrace();
                }
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

    public String getOwnHostName() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostName();
    }

    public CopyOnWriteArrayList<Message> getMessageQueue() {
        return messageQueue;
    }
}
