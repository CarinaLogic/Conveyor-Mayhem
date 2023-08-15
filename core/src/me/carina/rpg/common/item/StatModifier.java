package me.carina.rpg.common.item;

import me.carina.rpg.common.unit.stat.Affinity;
import me.carina.rpg.common.util.TripleMap;

public class StatModifier {
    StatModifierType type;
    Affinity affinity;
    float addValue;
    float mulValue;
    public StatModifier(){} //for json

}
