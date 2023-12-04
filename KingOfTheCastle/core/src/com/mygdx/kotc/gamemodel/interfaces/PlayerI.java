package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

import java.util.List;

public interface PlayerI {
    public void extendModifiers(Modifier modifier, List<Modifier> modifiers);
    public void updatePosition(Player player, Vec2d newPost);
}
