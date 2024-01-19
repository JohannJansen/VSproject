package com.mygdx.kotc.server;

import com.mygdx.kotc.applicationstub.ApplicationStub;
import com.mygdx.kotc.gamecontroller.GameControllerServer;
import com.mygdx.kotc.kotcrpc.ServerSkeleton;
import com.mygdx.kotc.kotcrpc.Status;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{

    public static ApplicationStub applicationStub;

//    public Server(GameControllerServer gameControllerServer) {
//        this.gameControllerServer = gameControllerServer;
//    }
//
//    public void startGameServer(){
//        gameControllerServer.s;
//    }
//
//    public static void main(String[] args) {
//        GameControllerServer gameControllerServer = new GameControllerServer();
//        Server server = new Server(gameControllerServer);
//        server.startGameServer();
//    }

    public static void main(String[] args) {
        applicationStub = new ApplicationStub(Status.SERVER);
    }
}
