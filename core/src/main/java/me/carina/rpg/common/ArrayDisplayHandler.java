package me.carina.rpg.common;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.Game;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.Map;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ArrayDisplayHandler<F extends Feature, D extends Actor & Display<F>>{
    //Stores () -> getIterable().get(i) at index i
    Array<Supplier<F>> suppliers = new Array<>();
    Group group;
    Class<D> cls;
    //Assuming add function can only append the Display instance to the tail of children, cannot insert
    Consumer<D> addFunc;
    Consumer<Actor> removeFunc;
    public ArrayDisplayHandler(Group group, Class<D> cls, Consumer<D> addFunc){
        this(group,cls,addFunc, Actor::remove);
    }
    public ArrayDisplayHandler(Group group, Class<D> cls, Consumer<D> addFunc, Consumer<Actor> removeFunc){
        this.group = group;
        this.cls = cls;
        this.addFunc = addFunc;
        this.removeFunc = removeFunc;
    }
    public abstract ArrayFeature<F> getIterable();
    public void tick() {
        ArrayFeature<F> iterable;
        try {
            iterable = getIterable();
        } catch (Exception ignored){
            return;
        }
        if (iterable == null) return;
        // make suppliers the same length as iterable, reusing instances
        if (suppliers.size < iterable.size()){
            for (int i = suppliers.size; i < iterable.size(); i++) {
                int finalI = i;
                suppliers.add(()-> getIterable().get(finalI));
            }
        }
        if (suppliers.size > iterable.size()){
            suppliers.removeRange(iterable.size(), suppliers.size-1);
        }
        SnapshotArray<Actor> children = group.getChildren();
        for (int i = 0; i < suppliers.size; i++) {
            D dis = Game.getClient().getDisplays().get(suppliers.get(i),cls);
            if (i < children.size){
                Actor child = children.get(i);
                //if child at correct index has the same supplier, do nothing
                if (child == dis) continue;
                //if child is the same class but has different supplier, set the supplier to those of correct index
                if (ClassReflection.isInstance(cls,child)){
                    //noinspection unchecked
                    D childD = (D) child;
                    childD.setFeatureSupplier(suppliers.get(i));
                    continue;
                }
                //if child is different class, remove all trailing children, add new instance
                children.removeRange(i,suppliers.size-1);
                addFunc.accept(dis);
            }
            else {
                //if child does not exist, add new instance
                addFunc.accept(dis);
            }
        }
    }
}
