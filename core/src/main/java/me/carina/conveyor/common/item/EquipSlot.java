package me.carina.conveyor.common.item;

import me.carina.conveyor.common.util.Array;

public abstract class EquipSlot {
    Equipment equipment;
    public EquipSlot(){}
    public abstract Array<EquipType> getAllowedTypes(Equipments equipments);

    public void setEquipment(Equipments equipments, Equipment equipment) {
        if (getAllowedTypes(equipments).contains(equipment.equipType,false)) this.equipment = equipment;
        else throw new RuntimeException();
    }

    public Equipment getEquipment() {
        return equipment;
    }
}
