package com.mygdx.kotc.gamemodel.factories;

import com.mygdx.kotc.gamemodel.entities.Equipment;
import com.mygdx.kotc.gamemodel.entities.EquipmentType;

public class EquipmentFactory {
    public static Equipment createSword(){
        return new Equipment(EquipmentType.SWORD, 18);
    }
    public static Equipment createBow(){
        return new Equipment(EquipmentType.BOW, 15);
    }
    public static Equipment createStaff(){
        return new Equipment(EquipmentType.STAFF, 15);
    }
}
