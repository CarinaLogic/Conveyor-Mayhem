package me.carina.rpg.common.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import me.carina.rpg.common.Feature;

import java.util.function.Consumer;
import java.util.function.Function;

public class FeatureArray<T extends Feature> extends Array<T>{
    Consumer<T> addFunction;
    Consumer<T> removeFunction;
    public FeatureArray(Consumer<T> addFunction, Consumer<T> removeFunction){
        this.addFunction = addFunction;
        this.removeFunction = removeFunction;
    }
    @Override
    public void add(T value) {
        super.add(value);
        addFunction.accept(value);
    }

    @SafeVarargs
    @Override
    public final void addAll(T... array) {
        super.addAll(array);
        for (T a : array) {
            addFunction.accept(a);
        }
    }

    @Override
    public void remove(T value) {
        super.remove(value);
        removeFunction.accept(value);
    }

    @Override
    public T removeIndex(int index) {
        removeFunction.accept(get(index));
        return super.removeIndex(index);
    }
}
