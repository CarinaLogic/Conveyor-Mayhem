package me.carina.rpg.common.unit;

import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.FeatureArray;

import java.util.Optional;

public class Units {
    transient BattleMap map;
    public Units(){} //for json
    FeatureArray<Unit> units = new FeatureArray<>(
            unit -> {if (map.hasDisplay()) map.getDisplay().addActor(unit.newDisplay());},
            unit -> {if (map.hasDisplay()) map.getDisplay().removeActor(unit.newDisplay());}
    );

    public Units(BattleMap map){
        this.map = map;
    }

    public Array<Unit> getUnits() {
        return units;
    }
    public void addUnit(Unit... units){
        this.units.addAll(units);

    }
    public void removeUnit(){

    }
}
