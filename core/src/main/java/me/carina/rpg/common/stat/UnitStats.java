package me.carina.rpg.common.stat;

import me.carina.rpg.common.util.Array;

import java.util.Objects;

public class UnitStats {
    Array<Stat> stats = new Array<>();
    public UnitStats(){
        stats.add(new Stat(StatType.hp,null));
        stats.add(new Stat(StatType.sp,null));
        for (Affinity.AffinityType affinity : Affinity.AffinityType.values()) {
            stats.add(new Stat(StatType.mp,new Affinity(affinity)));
            stats.add(new Stat(StatType.attack,new Affinity(affinity)));
            stats.add(new Stat(StatType.defence,new Affinity(affinity)));
            stats.add(new Stat(StatType.hit,new Affinity(affinity)));
            stats.add(new Stat(StatType.avoid,new Affinity(affinity)));
        }
    }
    public Stat getStat(StatType type, Affinity affinity){
        for (Stat stat : stats) {
            if (Objects.equals(stat.statType, type) && Objects.equals(stat.affinity, affinity)){
                return stat;
            }
        }
        return null;
    }
    public Stat getStat(StatType type){
        return getStat(type,null);
    }
}
