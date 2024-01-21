package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.gamecontroller.GameControllerServer;
import com.mygdx.kotc.kotcrpc.Message;
import com.mygdx.kotc.kotcrpc.MethodUtils;
import com.mygdx.kotc.kotcrpc.ServerSkeleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationStubServer {

    private ServerSkeleton serverSkeleton;
    private Map<String, Message> currentMoveForPlayers;
    private GameControllerServer gameControllerServer;

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

    public void callServerControllerMethod(String method, Object[] parameters) { //server
        MethodUtils.invokeMethod(gameControllerServer, method, parameters);
    }

    public void updateClientGamestates(String method, Object[] parameters){ //sendUpdatedState from server
        Message message = new Message(method, parameters);
        serverSkeleton.sendToAllClients(message);
    }



    public void hostLobby() {
        serverSkeleton.listenForIncomingCalls();
    }

    public Map<String, Message> getCurrentMoveForPlayers() {
        return currentMoveForPlayers;
    }
}
