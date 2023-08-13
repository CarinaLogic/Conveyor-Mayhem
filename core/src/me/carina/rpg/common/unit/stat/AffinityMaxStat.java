package me.carina.rpg.common.unit.stat;

public class AffinityMaxStat extends MaxStat{
    Affinity affinity;
    public AffinityMaxStat(){} //for json
    public AffinityMaxStat(Affinity affinity){
        this.affinity = affinity;
    }

    @Override
    public boolean matchAffinity(Affinity affinity) {
        return this.affinity.equals(affinity);
    }

    @Override
    public FractionalStat getBaseStat(Stats stats) {
        return stats.getStat(AffinityStat.class,affinity);
    }
}
