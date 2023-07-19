package me.carina.rpg.common.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Predicate;
import me.carina.rpg.Game;
import me.carina.rpg.common.Feature;

import java.util.function.Consumer;
import java.util.function.Function;

public class FeatureArray2D<T extends Feature> extends Array2D<T>{
    public FeatureArray2D(){} //for json
    public FeatureArray2D(int width, int height){
        super(width, height);
    }
    public void set(Feature parent, int x, int y, T obj) {
        if (obj == null){
            get(x,y).remove();
            return;
        }
        super.set(x, y, obj);
        if (Game.getInstance().isClient()) parent.getDisplay().addActor(obj.newDisplay());
    }
    //Do not use
    @Override
    public void set(int x, int y, T obj) {
        throw new RuntimeException();
    }

    @Override
    public void fill(T obj) {
        throw new RuntimeException();
    }

    public void fill(Feature parent, T obj){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < values.length / width; y++) {
                set(parent,x,y,obj);
            }
        }
    }

    @Override
    public void fill(Function<Vector2, T> func) {
        throw new RuntimeException();
    }

    public void fill(Feature parent, Function<Vector2,T> func){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                set(parent,x,y,func.apply(new Vector2(x,y)));
            }
        }
    }
}
