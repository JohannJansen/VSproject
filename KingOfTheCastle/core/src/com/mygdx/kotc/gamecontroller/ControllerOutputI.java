package com.mygdx.kotc.gamecontroller;

import com.mygdx.kotc.gamemodel.entities.Player;

import java.sql.Timestamp;
import java.util.List;

public interface ControllerOutputI {
    Timestamp getTime();
    Timestamp getRemainingTime();
    List<Player> getLobbyPlayerList();
}
