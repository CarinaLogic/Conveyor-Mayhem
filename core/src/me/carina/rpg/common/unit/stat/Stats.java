package me.carina.rpg.common.unit.stat;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.common.util.Array;

public class Stats {
    Array<Stat> stats = new Array<>();
    public Stats(){
        stats.add(new HPStat(), new HPMaxStat());
        stats.add(new SPStat(), new SPMaxStat());
        for (Affinity affinity : Affinity.values()) {
            stats.add(new AffinityStat(affinity), new AffinityMaxStat(affinity));
        }
    }
    public <T extends Stat> T getStat(Class<T> type){
        return stats.getByType(type);
    }
    public <T extends Stat> T getStat(Class<T> type, Affinity affinity){
        //noinspection unchecked
        return (T) stats.firstMatch(s -> ClassReflection.isInstance(type,s) && s.matchAffinity(affinity));
    }
}
