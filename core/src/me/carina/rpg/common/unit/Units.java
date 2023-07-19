package me.carina.rpg.common.unit;

import me.carina.rpg.Game;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.common.Context;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.FeatureArray;

import java.util.Optional;

public class Units extends Feature{
    FeatureArray<Unit> units = new FeatureArray<>();

    public Units(){} //for json

    @Override
    public Display newDisplay() {
        return new UnitsDisplay(this);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tickInner(Context context) {

    }

    public FeatureArray<Unit> getUnits() {
        return units;
    }

    public void addUnit(BattleMap map, Unit... units){
        this.units.addAll(map,units);

    }
    public void removeUnit(){

    }

    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }
}
