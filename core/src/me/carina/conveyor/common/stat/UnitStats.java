package me.carina.conveyor.common.stat;

import me.carina.conveyor.common.util.Array;

import java.util.Objects;

public class UnitStats {
    Array<Stat> stats = new Array<>();
    public UnitStats(){
        stats.add(new Stat(StatType.hp,null));
        stats.add(new Stat(StatType.sp,null));
        for (Affinity affinity : Affinity.values()) {
            stats.add(new Stat(StatType.ap,affinity));
            stats.add(new Stat(StatType.attack,affinity));
            stats.add(new Stat(StatType.defence,affinity));
            stats.add(new Stat(StatType.hit,affinity));
            stats.add(new Stat(StatType.avoid,affinity));
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
