package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Combat;
import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;

import java.util.List;

public interface GameStateOutputI {
    List<Player> getPlayerList();
    Map getMap();
    List<Combat> getCombatList();
    State getState();
}
