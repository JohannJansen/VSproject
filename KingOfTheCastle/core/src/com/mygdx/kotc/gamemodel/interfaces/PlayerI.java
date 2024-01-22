package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

import java.util.List;

public interface PlayerI {
    /**
     * extends the list of modifiers of a player
     * @param modifier the modifier to be added
     * @param modifiers the list of modifiers to be extended
     */
    void extendModifiers(Modifier modifier, List<Modifier> modifiers);

    /**
     * updates the position of a player
     * @param player the player to be updated
     * @param newPos the new position of the player
     */
    void updatePosition(Player player, Vec2d newPos);
}
