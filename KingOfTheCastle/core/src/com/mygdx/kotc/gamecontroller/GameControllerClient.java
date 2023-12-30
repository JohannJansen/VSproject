package com.mygdx.kotc.gamecontroller;

import com.mygdx.kotc.applicationstub.ApplicationStub;

public class GameControllerClient {
    private int playerID;
    private final ApplicationStub applicationStub = new ApplicationStub();

    public void start(){
        applicationStub.callServerControllerMethod("registerPlayer", new Object[]{});
    }
}
