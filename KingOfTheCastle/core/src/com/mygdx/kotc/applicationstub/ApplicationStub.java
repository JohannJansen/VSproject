package com.mygdx.kotc.applicationstub;

import com.mygdx.kotc.gamecontroller.GameControllerClient;
import com.mygdx.kotc.gamecontroller.GameControllerServer;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.kotcrpc.*;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationStub implements MultiplayerI{

//    private Status status;
    private Map<Long, Message> currentMoveForPlayer = new HashMap<>();
    private GameControllerServer gameControllerServer;
    private GameControllerClient gameControllerClient;
    private ClientStub clientStub;
    private ServerSkeleton serverSkeleton;
    private BlockingQueue<State> gameStateQueue;

    public ApplicationStub(Status status) {
        if(status == Status.CLIENT){
//            gameControllerClient.updateGameState();
            currentMoveForPlayer = new HashMap<>();
            clientStub = new ClientStub(this);
//            ExecutorService executorService = Executors.newSingleThreadExecutor();
//            executorService.submit(() -> clientStub.startListening());
//            executorService.shutdown();
        }else{
            serverSkeleton = new ServerSkeleton(this);
            gameControllerServer = new GameControllerServer(this);
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            // Submit tasks for the game loop and listening loop
            executorService.submit(gameControllerServer::start);
            executorService.submit(serverSkeleton::listenForIncomingCalls);
            // Shutdown the executor service when the application is done
            executorService.shutdown();
        }
    }

    @Override
    public void callServerControllerMethod(String method, Object[] parameters) { //server
        MethodUtils.invokeMethod(gameControllerServer, method, parameters);
    }

    public void invokeServerMethod(String method, Object[] parameters) { //client
        clientStub.invoke(method, parameters);
    }

    public void updateClientGamestates(String method, Object[] parameters){ //sendUpdatedState from server
        Message message = new Message(method, parameters);
        serverSkeleton.sendToAllClients(message);
    }

    @Override
    public void receiveGameState(String method, Object[] parameters) { //ran on client side
       MethodUtils.invokeMethod(gameControllerClient, method, parameters);
    }

    @Override
    public Message getCallForPlayer(int playerID) {
        return null;
    }

    @Override
    public void sendGameState(State state) {

    }

    @Override
    public void hostLobby() {
        serverSkeleton.listenForIncomingCalls();
    }

    @Override
    public void joinLobby(int lobbyID) {

    }

    public void joinServer(Player player){
        clientStub.connectToServer("DESKTOP-3UNJBSN", 8888);
        clientStub.invoke("registerPlayer", new Object[]{player});

    }

    public void setGameControllerClient(GameControllerClient gameControllerClient) {
        this.gameControllerClient = gameControllerClient;
    }

    public ClientStub getClientStub() {
        return clientStub;
    }
}
