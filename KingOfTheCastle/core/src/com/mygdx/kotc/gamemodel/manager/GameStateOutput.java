package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.Combat;
import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.interfaces.GameStateOutputI;

import java.util.List;

public class GameStateOutput implements GameStateOutputI {

    @Override
    public List<Player> getPlayerList() {
        return null;
    }

    @Override
    public Map getMap() {
        return null;
    }

    @Override
    public List<Combat> getCombatList() {
        return null;
    }

    @Override
    public State getState() {
        return null;
    }
}
