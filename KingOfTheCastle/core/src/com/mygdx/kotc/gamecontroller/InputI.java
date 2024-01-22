package com.mygdx.kotc.gamecontroller;

import com.mygdx.kotc.inputprocessors.inputevents.Event;

public interface InputI {
    /**
     * Sends an Input even to the Server, depending on the Screen the Player is currently in
     * @param event the Event to send to the Server
     */
    boolean sendInputEvent(Event event);
}
