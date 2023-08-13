package me.carina.rpg.common.unit.stat;

public class AffinityStat extends FractionalStat{
    Affinity affinity;
    public AffinityStat(){} //for json
    public AffinityStat(Affinity affinity){
        this.affinity = affinity;
    }

    @Override
    public boolean matchAffinity(Affinity affinity) {
        return this.affinity.equals(affinity);
    }

    @Override
    public MaxStat getMaxStat(Stats stats) {
        return stats.getStat(AffinityMaxStat.class,affinity);
    }
}
