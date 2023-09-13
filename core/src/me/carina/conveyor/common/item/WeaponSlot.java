package me.carina.conveyor.common.item;

import me.carina.conveyor.common.util.Array;

public class WeaponSlot extends EquipSlot{
    public WeaponSlot() {}

    @Override
    public Array<EquipType> getAllowedTypes(Equipments equipments) {
        return new Array<>(EquipType.weapon);
    }
}
