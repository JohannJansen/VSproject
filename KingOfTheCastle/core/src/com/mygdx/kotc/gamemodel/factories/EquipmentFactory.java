package com.mygdx.kotc.gamemodel.factories;

import com.mygdx.kotc.gamemodel.entities.Equipment;
import com.mygdx.kotc.gamemodel.entities.EquipmentType;

public class EquipmentFactory {
    public static Equipment createSword(){
        return new Equipment(EquipmentType.SWORD, 10);
    }
}
