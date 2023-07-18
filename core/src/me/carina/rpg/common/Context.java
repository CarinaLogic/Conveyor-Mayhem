package me.carina.rpg.common;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.common.util.Array;

public class Context implements Cloneable{
    Array<Object> contexts = new Array<>();
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> type){
        return (T) contexts.firstMatch(c -> ClassReflection.isInstance(type,c));
    }
    public void add(Object o){
        contexts.add(o);
    }

    @Override
    public Context clone(){
        try {
            return (Context) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
