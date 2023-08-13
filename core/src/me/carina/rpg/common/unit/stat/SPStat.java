package me.carina.rpg.common.unit.stat;

public class SPStat extends FractionalStat{
    @Override
    public MaxStat getMaxStat(Stats stats) {
        return stats.getStat(SPMaxStat.class);
    }
}
