package me.carina.rpg.common.item;

import me.carina.rpg.common.util.Array;

public abstract class EquipSlot {
    Equipments equipments;
    Equipment equipment;
    public EquipSlot(Equipments equipments){
        this.equipments = equipments;
    }
    public abstract Array<EquipType> getAllowedTypes();
}
