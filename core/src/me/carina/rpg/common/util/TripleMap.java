package me.carina.rpg.common.util;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;

import java.util.Iterator;
import java.util.Objects;

public class TripleMap<K1,K2,V> implements Iterable<TripleMap.Entry<K1,K2,V>>{
    ObjectMap<Key<K1,K2>,V> map;
    public TripleMap(){
        map = new ObjectMap<>();
    }
    public TripleMap(boolean ordered){
        if (ordered) map = new OrderedMap<>();
        else map = new ObjectMap<>();
    }
    public void put(K1 key1, K2 key2, V value){
        map.put(new Key<>(key1, key2),value);
    }
    public V get(K1 key1, K2 key2){
        return map.get(new Key<>(key1, key2));
    }
    public V get(K1 key1, K2 key2, V defaultValue){
        return map.get(new Key<>(key1, key2),defaultValue);
    }
    public boolean containsKey(K1 key1, K2 key2){
        return map.containsKey(new Key<>(key1, key2));
    }
    public Key<K1,K2> findKey(V value, boolean identity){
        return map.findKey(value,identity);
    }

    @Override
    public Iterator<Entry<K1, K2, V>> iterator() {
        return new Iterator<Entry<K1, K2, V>>() {
            @Override
            public boolean hasNext() {
                return map.iterator().hasNext();
            }

            @Override
            public Entry<K1, K2, V> next() {
                ObjectMap.Entry<Key<K1, K2>, V> entry = map.iterator().next();
                return new Entry<>(entry.key.key1,entry.key.key2,entry.value);
            }
        };
    }

    public record Key<K1,K2>(K1 key1, K2 key2){
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key<?, ?> key = (Key<?, ?>) o;
            return Objects.equals(key1, key.key1) && Objects.equals(key2, key.key2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key1, key2);
        }
    }
    public record Entry<K1,K2,V>(K1 key1, K2 key2, V value){}
}
