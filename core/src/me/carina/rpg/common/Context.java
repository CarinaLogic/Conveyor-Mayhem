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
        contexts.add(o);
    }

    public Context copy(){
        Context context = new Context();
        context.contexts = this.contexts.copy();
        return context;
    }
}
