package me.carina.rpg.common.unit.stat;

public class AffinityDefence extends MultiStat{
    Affinity affinity;
    public AffinityDefence(Affinity affinity){
        this.affinity = affinity;
    }

    @Override
    public boolean matchAffinity(Affinity affinity) {
        return this.affinity.equals(affinity);
    }
}
