package me.carina.rpg.common.unit.stat;

import me.carina.rpg.common.util.Array;

public abstract class Stat {
    float baseValue;
    float delta = 0;
    public float add(float value, Stats stats){
        float v = get();
        delta += value;
        if (baseValue + delta <= 0) delta = -baseValue;
        return get() - v;
    }
    public float get(){
        return baseValue + delta;
    }
    public void set(float value){
        delta = value - baseValue;
    }
    public void reset(){
        delta = 0;
    }
    public boolean matchAffinity(Affinity affinity){
        return false;
    }

}
