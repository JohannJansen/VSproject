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
    public void invoke(String method, Object[] parameters) {

        Message message = new Message(playerId, method, parameters);
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
        while (socket.isClosed()) {
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
                if (receivedGameState != null) {
                    Message message = unmarshallFromJson(receivedGameState);
                    assert message != null;
                    this.message = message;
                }
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

    public class PacketReader {
        public static void main(String[] args) {
            // Specify the desired kilobyte size
            int kilobyteSize = 1024;

            try (BufferedReader reader = new BufferedReader(new FileReader("example.txt"))) {
                StringBuilder packetBuilder = new StringBuilder();
                char[] buffer = new char[kilobyteSize];

                int bytesRead;
                while ((bytesRead = reader.read(buffer)) != -1) {
                    // Append the read characters to the packet builder
                    packetBuilder.append(buffer, 0, bytesRead);

                    // Check if the packet size has reached the desired kilobyte size
                    if (packetBuilder.length() >= kilobyteSize) {
                        // Process the kilobyte-sized packet
                        processPacket(packetBuilder.toString());

                        // Clear the packet builder for the next packet
                        packetBuilder.setLength(0);
                    }
                }

                // Process any remaining partial packet (if exists)
                if (packetBuilder.length() > 0) {
                    processPacket(packetBuilder.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void processPacket(String packet) {
            // Perform processing on the kilobyte-sized packet
            System.out.println("Received packet:\n" + packet);
            System.out.println("Packet size: " + packet.length() + " characters");
            System.out.println("----------");
        }
    }

    public Message getMessage() {
        return message;
    }

    //    public static void main(String[] args) {
//        ClientStub clientStub = new ClientStub();
//        clientStub.connectToServer("localhost",8888);
//        clientStub.call(-1, "johann", new Object[]{"test"});
//    }
}
