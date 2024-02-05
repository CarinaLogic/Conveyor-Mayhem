package me.carina.rpg.common.util;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Predicate;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

//Custom map implementation which extends libgdx object map (may change) with extra functionality
//I know it is VERY inefficient implementation, will fix it once situation is stable
public class Map<K,V> extends ObjectMap<K,V> {
    public void forEachKey(Consumer<K> consumer){
        for (K key : this.keys()) {
            consumer.accept(key);
        }
    }
    public void forEachValue(Consumer<V> consumer){
        for (V value : this.values()) {
            consumer.accept(value);
        }
    }
    public int countKey(Predicate<K> predicate){
        int c = 0;
        for (K key : this.keys()) {
            if (predicate.evaluate(key)) c++;
        }
        return c;
    }
    public int countValue(Predicate<V> predicate){
        int c = 0;
        for (V value : this.values()) {
            if (predicate.evaluate(value)) c++;
        }
        return c;
    }
    public int count(BiPredicate<K,V> predicate){
        int c = 0;
        for (Entry<K, V> entry : this) {
            if (predicate.test(entry.key,entry.value)) c++;
        }
        return c;
    }
    public <T> Map<K,T> evalKey(Function<K,T> func){
        Map<K,T> map = new Map<>();
        for (K key : this.keys()) {
            map.put(key, func.apply(key));
        }
        return map;
    }

    @Override
    public V remove(K key) {
        V v = super.remove(key);
        if (key instanceof Disposable) {
            Disposable d = (Disposable) key;
            d.dispose();
            return null;
        }
        return v;
    }

    public <T> Map<K,T> evalMatchingKey(Predicate<K> predicate, Function<K,T> func){
        Map<K,T> map = new Map<>();
        for (K key : this.keys()) {
            if (predicate.evaluate(key)) map.put(key, func.apply(key));
        }
        return map;
    }
    public <T> Map<V,T> evalValue(Function<V,T> func){
        Map<V,T> map = new Map<>();
        for (V value : this.values()) {
            map.put(value, func.apply(value));
        }
        return map;
    }
    public <T> Map<V,T> evalMatchingValue(Predicate<V> predicate, Function<V,T> func){
        Map<V,T> map = new Map<>();
        for (V value : this.values()) {
            if (predicate.evaluate(value)) map.put(value, func.apply(value));
        }
        return map;
    }
    public <T> TripleMap<K,V,T> eval(BiFunction<K,V,T> func){
        TripleMap<K,V,T> tripleMap = new TripleMap<>();
        for (Entry<K, V> entry : this) {
            tripleMap.put(entry.key,entry.value,func.apply(entry.key,entry.value));
        }
        return tripleMap;
    }
    public <T> TripleMap<K,V,T> evalMatching(BiPredicate<K,V> predicate, BiFunction<K,V,T> func){
        TripleMap<K,V,T> tripleMap = new TripleMap<>();
        for (Entry<K, V> entry : this) {
            if (predicate.test(entry.key,entry.value)) tripleMap.put(entry.key,entry.value,func.apply(entry.key,entry.value));
        }
        return tripleMap;
    }
}
