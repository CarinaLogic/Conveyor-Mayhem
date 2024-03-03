package me.carina.rpg.common.faction;

import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;
import me.carina.rpg.packets.S2CBattleRefreshPacket;

import java.lang.invoke.LambdaMetafactory;

public class Factions extends ArrayFeature<Faction> {
    transient Unit prevActiveUnit = null;

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
    public void tick() {
        super.tick();
        if (prevActiveUnit == null || prevActiveUnit.getWt() > 0){
            Unit activeUnit = getActiveUnit();
            if (activeUnit != null){
                prevActiveUnit = activeUnit;
                Game.getInstance().getConnections().sendAll(new S2CBattleRefreshPacket());
            }
            else {
                prevActiveUnit = null;
                for (Faction faction : this) {
                    for (Unit unit : faction.getUnits()) {
                        unit.setWt(unit.getWt() - 1);
                    }
                }
                Unit newActiveUnit = getActiveUnit();
                if (newActiveUnit != null){
                    prevActiveUnit = newActiveUnit;
                    Game.getInstance().getConnections().sendAll(new S2CBattleRefreshPacket());
                }
            }
        }
    }
    public Unit getActiveUnit(){
        int min = 0;
        Unit u = null;
        for (Faction faction : this) {
            for (Unit unit : faction.getUnits()) {
                if (unit.getWt() < min){
                    u = unit;
                    min = unit.getWt();
                }
            }
        }
        return u;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }
}
