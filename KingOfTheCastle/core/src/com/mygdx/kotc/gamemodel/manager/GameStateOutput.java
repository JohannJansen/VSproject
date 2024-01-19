package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.Combat;
import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.interfaces.GameStateOutputI;

import java.util.List;

public class GameStateOutput implements GameStateOutputI {
    PlayerManager playerManager = new PlayerManager();
    CombatManager combatManager = new CombatManager();
    MapManager mapManager = new MapManager();

    @Override
    public List<Player> getPlayerList() {
        return playerManager.getPlayerList();
    }

    @Override
    public Map getMap() {
        return mapManager.getMap();
    }

    @Override
    public List<Combat> getCombatList() {
        return combatManager.getActiveCombats();
    }

    @Override
    public State getState() { //use on server after a new move has been made from client and approved by server
        State state = new State();
        state.setCombatList(combatManager.getActiveCombats()); //use overridden methods
        state.setMap(mapManager.getMap());
        state.setPlayerList(playerManager.getPlayerList());
        return state;
    }
}
