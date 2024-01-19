package com.mygdx.kotc.gamemodel.entities;

import java.util.List;

public class State {
    private List<Player> playerList;
    private List<Combat> combatList;
    private Map map;

    public State() {}

    public State(List<Player> playerList, List<Combat> combatList, Map map) {
        this.playerList = playerList;
        this.combatList = combatList;
        this.map = map;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Combat> getCombatList() {
        return combatList;
    }

    public Map getMap() {
        return map;
    }

    public void setPlayerList(List<Player> newPlayerList){
        playerList = newPlayerList;
    }

    public void setCombatList(List<Combat> newCombatList){
        combatList = newCombatList;
    }

    public void setMap(Map newmap){
        map = newmap;
    }
}
