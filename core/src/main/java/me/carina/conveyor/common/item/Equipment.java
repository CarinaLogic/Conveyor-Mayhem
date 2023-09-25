package me.carina.conveyor.common.item;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.stat.Multiplier;
import me.carina.conveyor.common.util.Array;

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
