package com.mygdx.kotc.server;

import com.mygdx.kotc.applicationstub.ApplicationStubClient;
import com.mygdx.kotc.gamecontroller.GameControllerServer;
import com.mygdx.kotc.kotcrpc.Status;

public class Server{

    public static ApplicationStubClient applicationStubClient;

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
        GameControllerServer gameControllerServer = new GameControllerServer();
        gameControllerServer.run();
    }
}
