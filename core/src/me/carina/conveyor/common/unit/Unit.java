package me.carina.conveyor.common.unit;

import me.carina.conveyor.common.*;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.item.Equipments;
import me.carina.conveyor.common.skill.Skills;
import me.carina.conveyor.common.stat.StatusEffects;
import me.carina.conveyor.common.stat.UnitStats;

public class Unit extends Feature {
    String name = "Alice";
    int x;
    int y;
    Skills skills = new Skills();
    Equipments equipments = new Equipments();
    UnitStats stats = new UnitStats();
    StatusEffects effects = new StatusEffects();
    public UnitParts unitParts = new UnitParts();
    public Unit(){}//for json
    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
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

    public UnitStats getStats() {
        return stats;
    }

    public Equipments getEquipments() {
        return equipments;
    }

    public StatusEffects getEffects() {
        return effects;
    }
}
