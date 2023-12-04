package com.mygdx.kotc.gamemodel.entities;

import java.util.List;

public class State {
    private List<Player> playerList;
    private List<Combat> combatList;
    private Map map;

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Combat> getCombatList() {
        return combatList;
    }

    public Map getMap() {
        return map;
    }
}
