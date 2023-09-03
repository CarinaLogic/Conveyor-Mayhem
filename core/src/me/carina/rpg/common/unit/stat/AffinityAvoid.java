package me.carina.rpg.common.unit.stat;

public class AffinityAvoid extends MultiStat{
    Affinity affinity;
    public AffinityAvoid(Affinity affinity){
        this.affinity = affinity;
    }

    @Override
    public boolean matchAffinity(Affinity affinity) {
        return this.affinity.equals(affinity);
    }
}
