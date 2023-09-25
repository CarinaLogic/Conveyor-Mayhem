package me.carina.conveyor.common.faction;

import me.carina.conveyor.common.ArrayFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.unit.Unit;

public class Factions extends ArrayFeature<Faction> {

    public Factions(){
        add(new Faction());
    }

    public Faction getFaction(String name){
        return this.firstMatch(f -> f.name.equals(name));
    }
    public Faction getFaction(Unit unit){
        return this.firstMatch(f -> f.getUnits().contains(unit));
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }
}
