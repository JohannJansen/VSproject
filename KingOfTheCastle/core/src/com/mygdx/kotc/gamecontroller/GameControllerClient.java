package com.mygdx.kotc.gamecontroller;

import com.badlogic.gdx.Input;
import com.mygdx.kotc.applicationstub.ApplicationStub;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.gamemodel.manager.PlayerManager;
import com.mygdx.kotc.inputprocessors.inputevents.ButtonPressEvent;
import com.mygdx.kotc.inputprocessors.inputevents.MouseClickEvent;
import com.mygdx.kotc.inputprocessors.inputevents.Event;
import com.mygdx.kotc.screens.CurrentScreen;

public class GameControllerClient implements InputI{
    private int playerID;
    private final ApplicationStub applicationStub = new ApplicationStub();

    private CurrentScreen currentScreen;

    private CombatManager combatManager;

    private MapManager mapManager;

    private PlayerManager playerManager;

    public void start(){
        applicationStub.callServerControllerMethod("registerPlayer", new Object[]{});


    }


    @Override
    public void sendInputEvent(Event event) {
        if(event instanceof MouseClickEvent){
            handleMouseInput((MouseClickEvent) event);
        } else if (event instanceof ButtonPressEvent){
            handleButtonEvent((ButtonPressEvent) event);
        } else {
            throw new IllegalArgumentException("Event did not have a valid type");
        }
    }

    public void handleMouseInput(MouseClickEvent mouseClickEvent){

    }

    public void handleButtonEvent(ButtonPressEvent buttonPressEvent){
        if (currentScreen == CurrentScreen.MAP){
            handleButtonMap(buttonPressEvent);
        } else if (currentScreen == CurrentScreen.BATTLE){
            handleButtonBattle(buttonPressEvent);
        }
    }

    private void handleButtonMap(ButtonPressEvent buttonPressEvent){
        if (buttonPressEvent.keycode == Input.Keys.W) {
            applicationStub.callServerControllerMethod("moveplayer", new Object[]{playerManager.getPlayerById(playerID), new Vec2d(0,1)});
        }
    }

    private void handleButtonBattle(ButtonPressEvent buttonPressEvent){

    }
}
