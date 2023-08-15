package me.carina.rpg.common.unit.stat;

public abstract class MaxStat extends MultiStat {
    @Override
    public float add(float value, Stats stats) {
        float v = super.add(value,stats);
        if (getBaseStat(stats).get() >= get()) getBaseStat(stats).set(get());
        return v;
    }

    @Override
    public float mul(float value, Stats stats) {
        float v = super.mul(value, stats);
        if (getBaseStat(stats).get() >= get()) getBaseStat(stats).set(get());
        return v;
    }

    public void maxOutBase(Stats stats){
        getBaseStat(stats).maxOut(stats);
    }

    public abstract FractionalStat getBaseStat(Stats stats);
}
