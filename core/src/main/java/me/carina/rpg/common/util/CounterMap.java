package me.carina.rpg.common.util;

import com.badlogic.gdx.utils.ObjectIntMap;

public class CounterMap<T> extends Map<T,Integer> {
    public void up(T key){
        this.put(key,this.get(key,0)+1);
    }
    public void down(T key){
        this.put(key,this.get(key,1)-1);
    }

    @Override
    public <T1 extends T> Integer get(T1 key) {
        return super.get(key,0);
    }
}
