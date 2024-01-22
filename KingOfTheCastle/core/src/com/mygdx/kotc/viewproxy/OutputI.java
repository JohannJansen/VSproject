package com.mygdx.kotc.viewproxy;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.entities.Tile;

import java.util.List;

public interface OutputI {
    /**
     * converts the map to a list of renderable objects
     * @return the renderable objects
     */
    List<MapRenderData> mapToMapRenderData();

    /**
     * converts the combats into a list of renderable objects
     * @return the renderable objects
     */
    List<CombatRenderData> combatToCombatRenderData();

}
