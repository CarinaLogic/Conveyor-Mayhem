package me.carina.rpg.common.unit.stat;

public class AffinityAttack extends MultiStat{
    Affinity affinity;

    public AffinityAttack(Affinity affinity){
        this.affinity = affinity;
    }

    @Override
    public boolean matchAffinity(Affinity affinity) {
        return this.affinity.equals(affinity);
    }
}
