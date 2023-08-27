package me.carina.rpg.common.unit;

import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.item.Equipments;
import me.carina.rpg.common.skill.Skills;
import me.carina.rpg.common.unit.stat.MaxStat;
import me.carina.rpg.common.unit.stat.Stat;
import me.carina.rpg.common.unit.stat.Stats;

public class Unit extends Feature {
    String name = "LongLongName";
    int x;
    int y;
    Skills skills = new Skills();
    Equipments equipments = new Equipments();
    Stats stats = new Stats();
    public UnitParts unitParts = new UnitParts();
    public Unit(){}//for json
    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }

    public void reset(){
        for (Stat stat : stats.getStats()) {
            stat.reset();
        }
        equipments.getStatModifiers().modifyStats(stats);
        for (Stat stat : stats.getStats()) {
            if (stat instanceof MaxStat) {
                MaxStat maxStat = (MaxStat) stat;
                maxStat.maxOutBase(stats);
            }
        }
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.units;
    }

    @Override
    public void tick() {

    }

    public static class Def extends Feature.Def{

        @Override
        public void initFeature(Feature feature) {

        }
    }

    public Skills getSkills() {
        return skills;
    }

    public String getName() {
        return name;
    }

    public Stats getStats() {
        return stats;
    }
}
