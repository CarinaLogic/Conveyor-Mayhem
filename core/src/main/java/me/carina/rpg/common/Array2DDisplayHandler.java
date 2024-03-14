package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.Game;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.Array2D;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Array2DDisplayHandler<F extends Feature, D extends Actor & Display<F>> {
    Array2D<Supplier<F>> suppliers = new Array2D<>();
    Group group;
    Consumer<D> addFunc;
    Consumer<Actor> removeFunc;
    Class<D> cls;
    public Array2DDisplayHandler(Group group, Class<D> cls, Consumer<D> addFunc){
        this(group,cls,addFunc, Actor::remove);
    }
    public Array2DDisplayHandler(Group group, Class<D> cls, Consumer<D> addFunc, Consumer<Actor> removeFunc){
        this.group = group;
        this.cls = cls;
        this.addFunc = addFunc;
        this.removeFunc = removeFunc;
    }
    public abstract Array2DFeature<F> getIterable();
    public void tick() {
        Array2DFeature<F> iterable;
        try {
            iterable = getIterable();
        } catch (Exception ignored){
            return;
        }
        if (iterable == null) return;
        // make suppliers the same length as iterable, reusing instances
        suppliers.resize(iterable.size());
        suppliers.fillEmpty(vec -> () -> getIterable().get(vec));
        SnapshotArray<Actor> children = group.getChildren();
        for (int y = 0; y < iterable.getHeight(); y++) {
            for (int x = 0; x < iterable.getWidth(); x++) {
                D dis = Game.getClient().getDisplays().get(suppliers.get(x,y),cls);
                if (x + y * iterable.getWidth() < children.size) {
                    Actor child = children.get(x + y * iterable.getWidth());
                    //if child at correct index has the same supplier, do nothing
                    if (child == dis) continue;
                    //if child is the same class but has different supplier, set the supplier to those of correct index
                    if (ClassReflection.isInstance(cls, child)) {
                        //noinspection unchecked
                        D childD = (D) child;
                        childD.setFeatureSupplier(suppliers.get(x, y));
                        continue;
                    }
                    //if child is different class, remove all trailing children, add new instance
                    children.removeRange(x + y * iterable.getWidth(), children.size - 1);
                    addFunc.accept(dis);
                }
                else {
                    //if child does not exist, add new instance
                    addFunc.accept(dis);
                }
            }
        }
    }
}
