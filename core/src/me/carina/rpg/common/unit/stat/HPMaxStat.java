package me.carina.rpg.common.unit.stat;

public class HPMaxStat extends MaxStat{
    @Override
    public FractionalStat getBaseStat(Stats stats) {
        return stats.getStat(HPStat.class);
    }
}
