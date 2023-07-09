package me.carina.rpg.common.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import me.carina.rpg.common.Feature;

import java.util.function.Consumer;
import java.util.function.Function;

public class FeatureArray<T extends Feature> extends Array<T>{
    Feature parent;
    public FeatureArray(){} //for json
    public FeatureArray(Feature parent){
        this.parent = parent;
    }
    @Override
    public void add(T value) {
        super.add(value);
        if (parent.hasDisplay()) parent.getDisplay().addActor(value.newDisplay());
    }

    @SafeVarargs
    @Override
    public final void addAll(T... array) {
        super.addAll(array);
        for (T a : array) {
            if (parent.hasDisplay()) parent.getDisplay().addActor(a.newDisplay());
        }
    }

    @Override
    public void remove(T value) {
        super.remove(value);
        if (value.hasDisplay()) value.remove();
    }

    @Override
    public T removeIndex(int index) {
        Feature f = get(index);
        f.remove();
        return super.removeIndex(index);
    }
}
