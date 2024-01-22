package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.Combat;
import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.interfaces.GameStateOutputI;

import java.util.List;

public class GameStateOutput implements GameStateOutputI {
    PlayerManager playerManager;
    CombatManager combatManager;
    MapManager mapManager;

    public GameStateOutput(PlayerManager playerManager, CombatManager combatManager, MapManager mapManager) {
        this.playerManager = playerManager;
        this.combatManager = combatManager;
        this.mapManager = mapManager;
    }

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
    public State getState() {
        State state = new State();
        state.setCombatList(combatManager.getActiveCombats());
        state.setMap(mapManager.getMap());
        state.setPlayerList(playerManager.getPlayerList());
        return state;
    }


}
