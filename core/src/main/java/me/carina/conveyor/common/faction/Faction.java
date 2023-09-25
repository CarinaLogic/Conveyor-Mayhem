package me.carina.conveyor.common.faction;

import me.carina.conveyor.common.ChildFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.unit.Units;

public class Faction extends Feature {
    @ChildFeature
    Units units = new Units();
    String name = "";
    int team = 0;

    public boolean alliedWith(Faction faction){
        return this.team == faction.team;
    }


    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }

    public Units getUnits() {
        return units;
    }
}
