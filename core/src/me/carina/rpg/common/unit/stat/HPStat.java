package me.carina.rpg.common.unit.stat;

public class HPStat extends FractionalStat{
    @Override
    public MaxStat getMaxStat(Stats stats) {
        return stats.getStat(HPMaxStat.class);
    }
}
