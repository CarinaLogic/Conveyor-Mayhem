package me.carina.rpg.common.unit.newstat;

import me.carina.rpg.common.util.Array;

public class Stats {
    float base;
    Array<Multiplier> multipliers;
    public float get(){
        float add = 0;
        float mul = 0;
        for (Multiplier multiplier : multipliers) {
            add = ((1 - (add/base) * Math.signum(multiplier.addAmount)) * multiplier.addAmount) + add;
            mul = ((1 - mul * Math.signum(multiplier.addAmount)) * multiplier.mulAmount) + mul;
        }
        return base * mul + add;
    }
}
