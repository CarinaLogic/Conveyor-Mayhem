package me.carina.rpg.common.unit;

import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.item.Equipments;
import me.carina.rpg.common.unit.stat.Stat;
import me.carina.rpg.common.unit.stat.Stats;

public class Unit extends Feature {
    int x;
    int y;
    Equipments equipments = new Equipments();
    Stats stats = new Stats();
    @AutoDisplay
    public UnitParts unitParts = new UnitParts();
    public Unit(){}//for json
    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }

    @Override
    protected UnitDisplay newDisplay() {
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

    public Stats getStats() {
        return stats;
    }
}
