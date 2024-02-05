package me.carina.rpg.common.util;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;

import java.util.Iterator;

//Temp solution for now
//I will think of more memory-friendly way later
public class ArrayMap<K,V> implements Iterable<ArrayMap.Entry<K,V>> {
    public OrderedMap<K,Array<Integer>> keys = new OrderedMap<>();
    public IntMap<V> values = new IntMap<>();
    int index = 0;
    public void put(K key, V value){
        if (!keys.containsKey(key)) keys.put(key,new Array<>());
        index++;
        keys.get(key).add(index);
        values.put(index,value);
    }
    @SafeVarargs
    public final void put(K key, V... values){
        for (V value : values) {
            put(key, value);
        }
    }
    public Array<V> get(K key){
        if (keys.containsKey(key)) {
            Array<V> v = new Array<>();
            keys.get(key).forEach(i -> v.add(values.get(i)));
            return v;
        }
        return new Array<>();
    }
    public boolean containsKey(K key){
        return keys.containsKey(key);
    }
    public K findKey(V value, boolean identity){
        int i = values.findKey(value,identity,-1);
        if (i == -1) return null;
        for (ObjectMap.Entry<K, Array<Integer>> entry : keys) {
            if (entry.value.contains(i,false)) return entry.key;
        }
        return null;
    }
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            @Override
            public boolean hasNext() {
                return values.iterator().hasNext();
            }

            @Override
            public Entry<K, V> next() {
                IntMap.Entry<V> e = values.iterator().next();
                return new Entry<>(findKey(e.value,false),e.value);
            }
        };
    }

    public static class Entry<K,V>{
        K key;
        V value;
        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

}
