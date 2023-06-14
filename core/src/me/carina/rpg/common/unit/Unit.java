package me.carina.rpg.common.unit;

import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.item.Equipments;

public class Unit extends Feature {
    Equipments equipments = new Equipments();
    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }

    @Override
    public UnitDisplay newDisplay() {
        return new UnitDisplay(this);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.units;
    }

    public static class Def extends Feature.Def{

        @Override
        public void initFeature(Feature feature) {

        }
    }

    public Equipments getEquipments() {
        return equipments;
    }
}
