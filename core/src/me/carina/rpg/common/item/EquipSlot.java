package me.carina.rpg.common.item;

import me.carina.rpg.common.util.Array;

public abstract class EquipSlot {
    Equipment equipment;
    public EquipSlot(){}
    public abstract Array<EquipType> getAllowedTypes(Equipments equipments);

    public void setEquipment(Equipments equipments, Equipment equipment) {
        if (getAllowedTypes(equipments).contains(equipment.equipType,false)) this.equipment = equipment;
        else throw new RuntimeException();
    }
    public StatModifiers getStatModifiers(){
        if (equipment == null) return new StatModifiers();
        return equipment.statModifiers;
    }
}
