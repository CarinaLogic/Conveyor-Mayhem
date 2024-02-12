package me.carina.rpg.common;

import com.badlogic.gdx.utils.ObjectMap;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.util.Map;

import java.util.Iterator;

public class CounterMapFeature<T extends Feature> extends Feature implements Iterable<ObjectMap.Entry<T, Integer>>{
    Map<T,Integer> map = new Map<>();
    public void up(T key){
        map.put(key,map.get(key,0)+1);
    }
    public void down(T key){
        map.put(key,map.get(key,1)-1);
    }

    public <T1 extends T> Integer get(T1 key) {
        return map.get(key,0);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }

    @Override
    public Iterator<ObjectMap.Entry<T, Integer>> iterator() {
        return map.entries();
    }
}
