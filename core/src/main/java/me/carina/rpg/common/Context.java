package me.carina.rpg.common;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.common.util.Array;

public class Context {
    Array<Object> contexts = new Array<>();
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> type){
        return (T) contexts.firstMatch(c -> ClassReflection.isInstance(type,c));
    }
    public void add(Object o){
        if (o == null) return;
        for (Object context : contexts) {
            if (ClassReflection.isInstance(o.getClass(),context)){
                contexts.removeIdentity(context);
            }
        }
        contexts.add(o);
    }
    public <T> void remove(Class<T> type){
        for (Object context : contexts) {
            if (ClassReflection.isInstance(type,context)){
                contexts.removeIdentity(context);
            }
        }
    }

    public void reset(){
        contexts.clear();
    }

    public <T> T getLatest(Class<T> type){
        Object o = contexts.get(contexts.size - 1);
        if (ClassReflection.isInstance(type,o)){
            //noinspection unchecked
            return (T) o;
        }
        return null;
    }
}
