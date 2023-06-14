package me.carina.rpg.common.item;

import me.carina.rpg.common.util.Array;

public class ShieldSlot extends EquipSlot{
    public ShieldSlot(Equipments equipments) {
        super(equipments);
    }

    @Override
    public Array<EquipType> getAllowedTypes() {
        return new Array<>(EquipType.weapon,EquipType.shield);
    }
}
