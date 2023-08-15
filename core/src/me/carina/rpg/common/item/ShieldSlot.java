package me.carina.rpg.common.item;

import me.carina.rpg.common.util.Array;

public class ShieldSlot extends EquipSlot{
    public ShieldSlot() {}

    @Override
    public Array<EquipType> getAllowedTypes(Equipments equipments) {
        return new Array<>(EquipType.weapon,EquipType.shield);
    }
}
