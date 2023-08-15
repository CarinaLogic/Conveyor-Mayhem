package me.carina.rpg.common.item;

import me.carina.rpg.common.util.Array;

public class WeaponSlot extends EquipSlot{
    public WeaponSlot() {}

    @Override
    public Array<EquipType> getAllowedTypes(Equipments equipments) {
        return new Array<>(EquipType.weapon);
    }
}
