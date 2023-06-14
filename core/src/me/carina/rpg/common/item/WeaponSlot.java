package me.carina.rpg.common.item;

import me.carina.rpg.common.util.Array;

public class WeaponSlot extends EquipSlot{
    public WeaponSlot(Equipments equipments) {
        super(equipments);
    }

    @Override
    public Array<EquipType> getAllowedTypes() {
        return new Array<>(EquipType.weapon);
    }
}
