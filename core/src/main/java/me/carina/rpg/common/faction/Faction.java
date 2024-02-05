package me.carina.rpg.common.faction;

import me.carina.rpg.common.ChildFeature;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.unit.Units;

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
