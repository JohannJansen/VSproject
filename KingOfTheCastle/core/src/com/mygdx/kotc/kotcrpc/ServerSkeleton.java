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

    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private CopyOnWriteArrayList<Socket> connectedClients;
//    private ConcurrentHashMap<Long, Socket> iddClients;
    private CopyOnWriteArrayList<Message> messageQueue;


    public ServerSkeleton() {
        messageQueue = new CopyOnWriteArrayList<>();
//        iddClients = new ConcurrentHashMap<>();
        try {
            serverSocket = new ServerSocket(8888);
            executorService = Executors.newCachedThreadPool();
            connectedClients = new CopyOnWriteArrayList<>();
            System.out.println("Server started on port 8888");
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
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String receivedJson = reader.readLine();
            System.out.println("Received JSON from client: " + receivedJson);

            System.out.println("test");
            Message message = unmarshallFromJson(receivedJson);

            assert message != null;
            messageQueue.add(message);

            System.out.println(message.getMethodname());
            System.out.println(Arrays.toString(message.getParameters()));
            System.out.println("CLIENT SOCKET IS CONNECTED: " + clientSocket.isConnected());
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
            //System.out.println("CLIENT SOCKET IS CONNECTED: " + clientSocket.isClosed());
            if(!clientSocket.isClosed()) {
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    String marshalledMessage = marshallToJson(message);

                    int kilobyteSize = 1024;

                    for (int i = 0; i < marshalledMessage.length(); i += kilobyteSize) {
                        int end = Math.min(i + kilobyteSize, marshalledMessage.length());
                        String packet = marshalledMessage.substring(i, end);

                        // Write the packet to the buffer
                        writer.write(packet);
                    }
                    writer.write("\n");
                    writer.flush();
                    System.out.println("Game state sent to clients");
                } catch (IOException e) {
                    System.out.println("Error sending game state to client");
                    e.printStackTrace();
                }
            }
        }
    }

    public class MessageSplitter {
        public static void main(String[] args) {
            String message = "This is a long message that needs to be split into kilobyte-sized packets.";

            // Specify the desired kilobyte size
            int kilobyteSize = 1024;

            // Create a BufferedWriter with a custom buffer size
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("example.txt"))) {
                // Iterate over the message and split it into kilobyte-sized chunks
                for (int i = 0; i < message.length(); i += kilobyteSize) {
                    int end = Math.min(i + kilobyteSize, message.length());
                    String chunk = message.substring(i, end);

                    // Write the chunk to the buffer
                    writer.write(chunk);
                    // Optionally, write a newline or separator between chunks
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
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

    public CopyOnWriteArrayList<Message> getMessageQueue() {
        return messageQueue;
    }

}
