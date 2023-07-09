package me.carina.rpg.common.unit;

import me.carina.rpg.Game;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.FeatureArray;

import java.util.Optional;

public class Units {
    UnitArray units = new UnitArray();

    public Units(){} //for json

    public UnitArray getUnits() {
        return units;
    }

    public void addUnit(Unit... units){
        this.units.addAll(units);

    }
    public void removeUnit(){

    }

    public static class UnitArray extends FeatureArray<Unit>{
        @Override
        public Feature getParent() {
            return ((BattleScreen) ((Client) Game.getInstance()).getScreen()).getBattleMap();
        }
    }
}
