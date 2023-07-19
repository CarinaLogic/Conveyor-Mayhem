package me.carina.rpg.common.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import me.carina.rpg.Game;
import me.carina.rpg.common.Feature;

import java.util.function.Consumer;
import java.util.function.Function;

public class FeatureArray<T extends Feature> extends Array<T>{

    public FeatureArray(){} //for json

    public void add(Feature parent, T value) {
        super.add(value);
        if (Game.getInstance().isClient()) parent.getDisplay().addActor(value.newDisplay());
    }

    //Do not use
    @Override
    public void add(T value) {
        throw new RuntimeException();
    }

    @SafeVarargs
    public final void addAll(Feature parent, T... array) {
        super.addAll(array);
        for (T a : array) {
            if (Game.getInstance().isClient()) parent.getDisplay().addActor(a.newDisplay());
        }
    }

    //Do not use
    @SafeVarargs
    @Override
    public final void addAll(T... array) {
        throw new RuntimeException();
    }

    @Override
    public void remove(T value) {
        super.remove(value);
        if (Game.getInstance().isClient()) value.remove();
    }

    @Override
    public T removeIndex(int index) {
        Feature f = get(index);
        f.remove();
        return super.removeIndex(index);
    }
}
