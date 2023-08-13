package me.carina.rpg.common.unit.stat;

//Used for Atk/Def
public abstract class MultiStat extends Stat {
    float minAddFraction = 0.5f;
    float maxAddFraction = 2;
    float multi = 1;
    float minMulti = 0.5f;
    float maxMulti = 2;

    @Override
    public float add(float value, Stats stats) {
        float d = delta;
        float a = delta / baseValue;
        if (value <= 0) {
            value = value * (1 + a);
        } else {
            value = value * (1 - a);
        }
        delta += value;
        if (delta / baseValue <= minAddFraction) delta = baseValue * minAddFraction;
        if (delta / baseValue >= maxAddFraction) delta = baseValue * maxAddFraction;
        return delta - d;
    }

    public float mul(float value, Stats stats) {
        float m = multi;
        if (value <= 0){
            value = value * multi;
        }
        else {
            value = value * (2-multi);
        }
        multi += value;
        if (multi <= minMulti) multi = minMulti;
        if (multi >= maxMulti) multi = maxMulti;
        return multi - m;
    }

    @Override
    public float get() {
        return baseValue * multi + delta;
    }
}
