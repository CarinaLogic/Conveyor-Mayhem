package me.carina.rpg.common.item;

import me.carina.rpg.common.unit.stat.MaxStat;
import me.carina.rpg.common.unit.stat.MultiStat;
import me.carina.rpg.common.unit.stat.Stat;
import me.carina.rpg.common.unit.stat.Stats;
import me.carina.rpg.common.util.Array;

import java.util.Objects;

public class StatModifiers {
    Array<StatModifier> modifiers = new Array<>();
    public StatModifiers(){} //for json
    public void add(StatModifier modifier){
        for (StatModifier statModifier : modifiers) {
            if (Objects.equals(statModifier.type, modifier.type) &&
                    Objects.equals(statModifier.affinity,modifier.affinity)){
                modifier.addValue += statModifier.addValue;
                modifier.mulValue += statModifier.mulValue;
                return;
            }
        }
        modifiers.add(modifier);
    }
    public void add(StatModifiers modifiers){
        for (StatModifier modifier : modifiers.getModifiers()) {
            this.add(modifier);
        }
    }

    public void modifyStats(Stats stats){
        for (StatModifier modifier : modifiers) {
            Stat s;
            if (modifier.affinity != null){
                s = stats.getStat(modifier.type.getType(), modifier.affinity);
            }
            else {
                s = stats.getStat(modifier.type.getType());
            }
            s.add(modifier.addValue,stats);
            if (s instanceof MultiStat) {
                MultiStat multiStat = (MultiStat) s;
                multiStat.mul(modifier.mulValue, stats);
            }
        }
    }

    public Array<StatModifier> getModifiers() {
        return modifiers;
    }
}
