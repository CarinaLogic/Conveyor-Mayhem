package me.carina.rpg.common.unit.stat;

public class AffinityHit extends MultiStat{
    Affinity affinity;
    public AffinityHit(Affinity affinity){
        this.affinity = affinity;
    }

    @Override
    public boolean matchAffinity(Affinity affinity) {
        return this.affinity.equals(affinity);
    }
}
