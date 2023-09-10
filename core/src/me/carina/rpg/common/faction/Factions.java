package me.carina.rpg.common.faction;

import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.unit.Unit;

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
    public Class<? extends Definition> getDefClass() {
        return null;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }
}
