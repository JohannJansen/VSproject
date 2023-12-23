package com.mygdx.kotc.viewproxy;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.kotc.gamemodel.entities.State;
import com.mygdx.kotc.gamemodel.entities.Tile;

import java.util.List;

public interface OutputI {
    public List<Rectangle> stateToRenderableObject();
}
