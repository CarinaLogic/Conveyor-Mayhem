package me.carina.rpg.common.item;

import me.carina.rpg.common.util.Array;

public class ArmorSlot extends EquipSlot{
    public ArmorSlot() {}

    @Override
    public Array<EquipType> getAllowedTypes(Equipments equipments) {
        Array<EquipType> a = new Array<>(EquipType.body,EquipType.glove,EquipType.hat,EquipType.shoes);
        equipments.armorSlots.eval(s -> s.equipment.equipType).forEachValue(a::remove);
        a.add(EquipType.misc);
        return a;
    }
}
