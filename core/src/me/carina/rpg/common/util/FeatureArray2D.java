package me.carina.rpg.common.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Predicate;
import me.carina.rpg.common.Feature;

import java.util.function.Consumer;

public class FeatureArray2D<T extends Feature> extends Array2D<T>{
    Feature parent;
    public FeatureArray2D(){} //for json
    public FeatureArray2D(Feature parent, int width, int height){
        super(width, height);
        this.parent = parent;
    }
    @Override
    public void set(int x, int y, T obj) {
        if (obj == null){
            get(x,y).remove();
            return;
        }
        super.set(x, y, obj);
        if (parent.hasDisplay()) parent.getDisplay().addActor(obj.newDisplay());
    }
}
