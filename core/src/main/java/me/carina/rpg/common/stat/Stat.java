package me.carina.rpg.common.stat;

import me.carina.rpg.common.item.EquipSlot;
import me.carina.rpg.common.item.Equipment;
import me.carina.rpg.common.unit.Unit;

import java.util.Objects;

public class Stat {
    Affinity affinity;
    StatType statType;
    float base;
    float currentValue;
    public Stat(){}
    public Stat(StatType type, Affinity affinity){
        this.statType = type;
        this.affinity = affinity;
    }

    public float getBase(){
        return base;
    }
    public float get(Unit unit){
        if (statType.isVariable()) return currentValue;
        float add = 0;
        float mul = 0;
        for (EquipSlot slot : unit.getEquipments().getAllSlots()) {
            Equipment equipment = slot.getEquipment();
            if (equipment != null){
                for (Multiplier multiplier : equipment.getMultipliers()) {
                    if (Objects.equals(multiplier.targetStat, statType) && Objects.equals(multiplier.targetAffinity, affinity)){
                        add = ((1 - (add/base) * Math.signum(multiplier.addAmount)) * multiplier.addAmount) + add;
                        mul = ((1 - mul * Math.signum(multiplier.addAmount)) * multiplier.mulAmount) + mul;
                    }
                }
            }
        }
        for (StatusEffect effect : unit.getEffects()) {
            if (effect instanceof Multiplier) {
                Multiplier multiplier = (Multiplier) effect;
                if (Objects.equals(multiplier.targetStat, statType) && Objects.equals(multiplier.targetAffinity, affinity)){
                    add = ((1 - (add/base) * Math.signum(multiplier.addAmount)) * multiplier.addAmount) + add;
                    mul = ((1 - mul * Math.signum(multiplier.addAmount)) * multiplier.mulAmount) + mul;
                }
            }
        }
        return base * mul + add;
    }
    public float getMax(Unit unit){
        float add = 0;
        float mul = 0;
        for (EquipSlot slot : unit.getEquipments().getAllSlots()) {
            Equipment equipment = slot.getEquipment();
            if (equipment != null){
                for (Multiplier multiplier : equipment.getMultipliers()) {
                    if (Objects.equals(multiplier.targetStat, statType) && Objects.equals(multiplier.targetAffinity, affinity)){
                        add = ((1 - (add/base) * Math.signum(multiplier.addAmount)) * multiplier.addAmount) + add;
                        mul = ((1 - mul * Math.signum(multiplier.addAmount)) * multiplier.mulAmount) + mul;
                    }
                }
            }
        }
        for (StatusEffect effect : unit.getEffects()) {
            if (effect instanceof Multiplier) {
                Multiplier multiplier = (Multiplier) effect;
                if (multiplier.targetStat.equals(statType) && multiplier.targetAffinity.equals(affinity)){
                    add = ((1 - (add/base) * Math.signum(multiplier.addAmount)) * multiplier.addAmount) + add;
                    mul = ((1 - mul * Math.signum(multiplier.addAmount)) * multiplier.mulAmount) + mul;
                }
            }
        }
        return base * mul + add;
    }
}
