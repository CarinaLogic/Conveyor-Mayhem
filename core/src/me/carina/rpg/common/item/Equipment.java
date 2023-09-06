package me.carina.rpg.common.item;

import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.unit.stat.Affinity;
import me.carina.rpg.common.unit.stat.Multiplier;
import me.carina.rpg.common.util.Array;

public class Equipment extends Item{
    EquipType equipType;
    Array<Affinity> affinities = new Array<>();
    Array<Multiplier> multipliers = new Array<>();

    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }
    public static class Def extends Feature.Def {
        EquipType equipType;
        Array<Affinity> affinities;
        Array<Multiplier> multipliers;
        @Override
        public void initFeature(Feature feature) {
            if (feature instanceof Equipment) {
                Equipment equipment = (Equipment) feature;
                equipment.equipType = equipType;
                equipment.affinities = affinities;
                equipment.multipliers = multipliers;
            }
        }
    }
}
