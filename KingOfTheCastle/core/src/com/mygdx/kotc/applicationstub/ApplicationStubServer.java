package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.kotcrpc.Message;
import com.mygdx.kotc.kotcrpc.ServerSkeleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationStubServer implements ApplicationStubIServer{

    private final ServerSkeleton serverSkeleton;
    private final Map<String, Message> currentMoveForPlayers;

    public ApplicationStubServer() {
        currentMoveForPlayers = new HashMap<>();
        serverSkeleton = new ServerSkeleton();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(serverSkeleton::listenForIncomingCalls);
        // Shutdown the executor service when the application is done
        executorService.shutdown();
    }

    public void updateCurrentMove(){
        while (!serverSkeleton.getMessageQueue().isEmpty()){
            Message message = serverSkeleton.getMessageQueue().remove(0);
            currentMoveForPlayers.put(message.getPlayerId(), message);
        }
    }

    public void updateClientGamestates(String method, Object[] parameters){ //sendUpdatedState from server
        Message message = new Message(method, parameters);
        serverSkeleton.sendToAllClients(message);
    }

    public Map<String, Message> getCurrentMoveForPlayers() {
        return currentMoveForPlayers;
    }
}
