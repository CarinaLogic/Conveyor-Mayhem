package me.carina.conveyor.common.item;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.stat.Multiplier;
import me.carina.conveyor.common.util.Array;

public class Equipment extends Item{
    EquipType equipType;
    Array<Multiplier> multipliers = new Array<>();

    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }
    public static class Def extends Feature.Def {
        EquipType equipType;
        Array<Multiplier> multipliers;
        @Override
        public void initFeature(Feature feature) {
            if (feature instanceof Equipment) {
                Equipment equipment = (Equipment) feature;
                equipment.equipType = equipType;
                equipment.multipliers = multipliers;
            }
        }
    }

    public Array<Multiplier> getMultipliers() {
        return multipliers;
    }
}
