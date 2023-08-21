package me.carina.rpg.common;

import com.badlogic.gdx.utils.Predicate;
import me.carina.rpg.common.util.Array;

import java.util.Iterator;

//Wrapper for array
public abstract class ArrayFeature<T extends Feature> extends Feature implements Iterable<T>{
    //Add things as needed
    Array<T> array = new Array<>();

    @SafeVarargs
    public final void add(T... items){
        array.addAll(items);
    }

    public T get(int index){
        return array.get(index);
    }

    @SafeVarargs
    public final void remove(T... item){
        array.remove(item);
    }

    public int size(){
        return array.size;
    }

    public T firstMatch(Predicate<T> predicate){
        return array.firstMatch(predicate);
    }

    public boolean contains(T item){
        return array.contains(item,false);
    }

    public boolean containsIdentity(T item){
        return array.contains(item,true);
    }


    public Array<T> getArray() {
        return array;
    }

    @Override
    public Iterator<T> iterator() {
        return new com.badlogic.gdx.utils.Array.ArrayIterator<>(array);
    }
}
