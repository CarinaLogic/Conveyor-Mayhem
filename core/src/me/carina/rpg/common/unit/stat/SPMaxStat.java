package me.carina.rpg.common.unit.stat;

public class SPMaxStat extends MaxStat{
    @Override
    public FractionalStat getBaseStat(Stats stats) {
        return stats.getStat(SPStat.class);
    }
}
