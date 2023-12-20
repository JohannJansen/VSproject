package com.mygdx.kotc.gamemodel.factories;

import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

    public static Player createTestPlayer(){
        List<Modifier> attackList = new ArrayList<>();
        List<Modifier> defenseList = new ArrayList<>();
        Vec2d v2d = new Vec2d(8,9);
        return new Player(null,false,19,300,50,300,
                0, EquipmentFactory.createSword(), null,null,attackList,defenseList,false);
    }

    public static Player createFighter(){
        //TODO
        return null;
    }

}
