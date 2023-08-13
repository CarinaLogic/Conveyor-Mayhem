package me.carina.rpg.common.unit.stat;

public abstract class FractionalStat extends Stat {
    @Override
    public float add(float value, Stats stats) {
        float v = get();
        delta += value;
        if (baseValue + delta <= 0) delta = -baseValue;
        float max = getMaxStat(stats).get();
        if (get() >= max) set(max);
        return get() - v;
    }

    public abstract MaxStat getMaxStat(Stats stats);
}
