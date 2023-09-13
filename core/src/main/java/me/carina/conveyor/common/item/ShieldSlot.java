package me.carina.conveyor.common.item;

import me.carina.conveyor.common.util.Array;

public class ShieldSlot extends EquipSlot{
    public ShieldSlot() {}

    @Override
    public Array<EquipType> getAllowedTypes(Equipments equipments) {
        return new Array<>(EquipType.weapon,EquipType.shield);
    }
}
