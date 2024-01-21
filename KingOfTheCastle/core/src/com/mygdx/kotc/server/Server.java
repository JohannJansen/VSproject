package com.mygdx.kotc.server;

import com.mygdx.kotc.gamecontroller.GameControllerServer;

public class Server{
    public static void main(String[] args) {
        GameControllerServer gameControllerServer = new GameControllerServer();
        gameControllerServer.run();
    }
}
