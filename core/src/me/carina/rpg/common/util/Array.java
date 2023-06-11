package me.carina.rpg.common.util;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Predicate;

import java.util.function.BooleanSupplier;
import java.util.function.Function;

//Custom array implementation which extends libgdx array (may change) with extra functionality
//I know it is VERY inefficient implementation, will fix it once situation is stable
public class Array<T> extends com.badlogic.gdx.utils.Array<T> {
    public Array<T> match(Predicate<T> predicate){
        Array<T> array = new Array<>();
        for (T item : this.select(predicate)) {
            array.add(item);
        }
        return array;
    }
    public int count(Predicate<T> predicate){
        int c = 0;
        for (T item : this) {
            if (predicate.evaluate(item)) c++;
        }
        return c;
    }
    public <V> Map<T,V> evalMatching(Predicate<T> predicate, Function<T,V> func){
        Map<T,V> map = new Map<>();
        for (T item : this.select(predicate)) {
            map.put(item,func.apply(item));
        }
        return map;
    }
    public <V>  Map<T,V> eval(Function<T,V> func){
        Map<T,V> map = new Map<>();
        for (T item : this) {
            map.put(item, func.apply(item));
        }
        return map;
    }
}
