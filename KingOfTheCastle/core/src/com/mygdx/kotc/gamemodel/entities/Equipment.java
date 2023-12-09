package com.mygdx.kotc.gamemodel.entities;

public class Equipment {
    /**
     *
     */
    private EquipmentType equipmentType;
    private int equipmentValue;

    public Equipment(EquipmentType equipmentType, int equipmentValue) {
        this.equipmentType = equipmentType;
        this.equipmentValue = equipmentValue;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public int getEquipmentValue() {
        return equipmentValue;
    }

    public void setEquipmentValue(int equipmentValue) {
        this.equipmentValue = equipmentValue;
    }
}
