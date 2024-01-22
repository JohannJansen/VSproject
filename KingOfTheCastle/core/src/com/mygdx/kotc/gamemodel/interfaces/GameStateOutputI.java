package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Combat;
import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;

import java.util.List;

public interface GameStateOutputI {
    /**
     * @return returns the List of players
     */
    List<Player> getPlayerList();

    /**
     * @return returns a Map
     */
    Map getMap();

    /**
     * @return Returns a list of all Active Combats
     */
    List<Combat> getCombatList();

    /**
     * @return Returns the Game state
     */
    State getState();
}
