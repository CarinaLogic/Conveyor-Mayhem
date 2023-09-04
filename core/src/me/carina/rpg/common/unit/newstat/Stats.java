package me.carina.rpg.common.unit.newstat;

import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.util.Array;

public class Stats {
    Affinity affinity;
    StatType statType;
    float base;
    public float get(Unit unit){
        float add = 0;
        float mul = 0;
        for (StatusEffect effect : unit.getEffects()) {
            if (effect instanceof Multiplier) {
                Multiplier multiplier = (Multiplier) effect;
                if (multiplier.targetStat.equals(statType) && multiplier.targetAffinity.equals(affinity) && statType.canMultiply()){
                    add = ((1 - (add/base) * Math.signum(multiplier.addAmount)) * multiplier.addAmount) + add;
                    mul = ((1 - mul * Math.signum(multiplier.addAmount)) * multiplier.mulAmount) + mul;
                }
            }
        }
        return base * mul + add;
    }
}
