package com.mygdx.kotc.gamemodel.factories;

import com.mygdx.kotc.gamemodel.entities.Tile;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

public class TileFactory {
    /**
     * Mein Bruder im geiste LIES die Namen der Methoden und all deine Fragen sollten beantwortet sein amk
     * @param pos Die position welche das Tile haben soll
     * @return gibt das Fertige Tile zurück
     */
    public static Tile createDefaultTile(Vec2d pos){
        Tile tile = new Tile();
        tile.setTraversable(true);
        tile.setPosition(pos);
        return tile;
    }

    public static Tile createUntraversableTile(Vec2d pos){
        Tile tile = new Tile();
        tile.setTraversable(false);
        tile.setPosition(pos);
        return tile;
    }
}
