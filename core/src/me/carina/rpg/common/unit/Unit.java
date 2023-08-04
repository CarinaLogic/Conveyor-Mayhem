package me.carina.rpg.common.unit;

import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.item.Equipments;

public class Unit extends Feature {
    int x;
    int y;
    Equipments equipments = new Equipments();
    @AutoDisplay
    UnitParts unitParts = new UnitParts();
    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }

    @Override
    public UnitDisplay newDisplay() {
        UnitDisplay d = new UnitDisplay(this);
        setDisplay(d);
        return d;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.units;
    }

    @Override
    public void tick(Context context) {

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
