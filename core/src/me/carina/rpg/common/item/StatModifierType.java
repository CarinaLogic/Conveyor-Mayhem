package me.carina.rpg.common.item;

import me.carina.rpg.common.unit.stat.*;

public enum StatModifierType {
    max_hp(HPMaxStat.class), max_sp(SPMaxStat.class), affinity(AffinityMaxStat.class) ,affinity_atk(AffinityAttack.class), affinity_def(AffinityDefence.class);
    final Class<? extends Stat> type;
    StatModifierType(Class<? extends Stat> type){
        this.type = type;
    }

    public Class<? extends Stat> getType() {
        return type;
    }
}
