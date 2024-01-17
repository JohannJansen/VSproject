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
        return new Player(6,null, false,19,300,50,300,
                0, EquipmentFactory.createSword(), null,null,attackList,defenseList,false);
    }

    public static Player createWizzard(){
        List<Modifier> attackList = new ArrayList<>();
        List<Modifier> defenseList = new ArrayList<>();
        Vec2d v2d = new Vec2d(7,9);
        return new Player(1,null, false,15,200,80,200,
                10, EquipmentFactory.createStaff(), null,null,attackList,defenseList,false);
    }
    public static Player createArcher(){
        List<Modifier> attackList = new ArrayList<>();
        List<Modifier> defenseList = new ArrayList<>();
        Vec2d v2d = new Vec2d(2,9);
        return new Player(2,null, false,25,220,85,220,
                20, EquipmentFactory.createBow(), null,null,attackList,defenseList,false);
    }
    public static Player createMonk(){
        List<Modifier> attackList = new ArrayList<>();
        List<Modifier> defenseList = new ArrayList<>();
        Vec2d v2d = new Vec2d(3,9);
        return new Player(3,null, false,11,400,40,400,
                18, EquipmentFactory.createStaff(), null,null,attackList,defenseList,false);
    }
    public static Player createknight(){
        List<Modifier> attackList = new ArrayList<>();
        List<Modifier> defenseList = new ArrayList<>();
        Vec2d v2d = new Vec2d(7,9);
        return new Player(5,null, false,7,350,180,350,
                20, EquipmentFactory.createSword(), null,null,attackList,defenseList,false);
    }

}
