package me.carina.rpg.common.util;

import com.badlogic.gdx.math.Vector2;
import me.carina.rpg.common.Feature;

import java.util.function.Consumer;

public class FeatureArray2D<T extends Feature> extends Array2D<T>{
    Consumer<T> addFunction;
    Consumer<T> removeFunction;
    public FeatureArray2D(){} //for json
    public FeatureArray2D(int width, int height, Consumer<T> addFunction, Consumer<T> removeFunction){
        super(width, height);
        this.addFunction = addFunction;
        this.removeFunction = removeFunction;
    }
    @Override
    public void set(int x, int y, T obj) {
        super.set(x, y, obj);
        if (obj == null) removeFunction.accept(get(x, y));
        addFunction.accept(obj);
    }
}
