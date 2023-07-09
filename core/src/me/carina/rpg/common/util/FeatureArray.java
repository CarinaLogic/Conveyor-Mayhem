package me.carina.rpg.common.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import me.carina.rpg.Game;
import me.carina.rpg.common.Feature;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class FeatureArray<T extends Feature> extends Array<T>{
    //Extend this class and override gtParent() as needed
    //Do not use inline declaration (lambdas) as it will break json serialization
    //Do not use non-static child classes, it will also break serialization
    //Please find a way to grab the feature you need from static context
    public FeatureArray(){} //for json
    @Override
    public void add(T value) {
        super.add(value);
        if (Game.getInstance().isClient()) getParent().getDisplay().addActor(value.newDisplay());
    }

    @SafeVarargs
    @Override
    public final void addAll(T... array) {
        super.addAll(array);
        for (T a : array) {
            if (Game.getInstance().isClient()) getParent().getDisplay().addActor(a.newDisplay());
        }
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
    public abstract Feature getParent();
}
