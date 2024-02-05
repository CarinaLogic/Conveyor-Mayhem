package me.carina.rpg.common.unit;

import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.item.Equipments;
import me.carina.rpg.common.skill.Skills;
import me.carina.rpg.common.stat.StatusEffects;
import me.carina.rpg.common.stat.UnitStats;

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
    public AssetGroup getAssetGroup() {
        return AssetGroup.units;
    }

    @Override
    public void tick() {

    }

    public static class Def extends Definition<Unit>{

        @Override
        public Class<Unit> getDefinedClass() {
            return Unit.class;
        }

        @Override
        public void init(Unit definedObject) {

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
