package me.carina.rpg.common.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Predicate;
import me.carina.rpg.Game;
import me.carina.rpg.common.Feature;

import java.util.function.Consumer;

public abstract class FeatureArray2D<T extends Feature> extends Array2D<T>{
    public FeatureArray2D(){} //for json
    public FeatureArray2D(int width, int height){
        super(width, height);
    }
    @Override
    public void set(int x, int y, T obj) {
        if (obj == null){
            get(x,y).remove();
            return;
        }
        super.set(x, y, obj);
        if (Game.getInstance().isClient()) getParent().getDisplay().addActor(obj.newDisplay());
    }
    public abstract Feature getParent();
}
