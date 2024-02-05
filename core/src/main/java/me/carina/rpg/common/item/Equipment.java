package me.carina.rpg.common.item;

import me.carina.rpg.common.Definition;
import me.carina.rpg.common.stat.Multiplier;
import me.carina.rpg.common.util.Array;

public class Equipment extends Item{
    EquipType equipType;
    Array<Multiplier> multipliers = new Array<>();

    public static class Def extends Definition<Equipment> {
        EquipType equipType;
        Array<Multiplier> multipliers;

        @Override
        public Class<Equipment> getDefinedClass() {
            return Equipment.class;
        }

        @Override
        public void init(Equipment definedObject) {
            definedObject.equipType = equipType;
            definedObject.multipliers = multipliers;
        }
    }

    public Array<Multiplier> getMultipliers() {
        return multipliers;
    }
}
