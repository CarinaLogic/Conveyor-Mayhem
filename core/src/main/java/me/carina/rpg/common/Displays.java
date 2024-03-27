package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.PooledLinkedList;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.Game;
import me.carina.rpg.client.battle.UIBattleStatPanel;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.Map;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public final class Displays {
    //TODO fix performance
    Map<Supplier<?>, Array<Display<?>>> displays = new Map<>();
    OrderedMap<Feature, Array<Display<?>>> featureCache = new OrderedMap<>();
    /**
     * *WARNING: Use static Supplier object*
     */
    public <F extends Feature, D extends Actor & Display<F>> D get(Supplier<F> supplier, Class<D> cls){
        //if supplier itself is null, return
        if (supplier == null) return null;
        //if supplier is the same instance as the registered one
        Array<Display<?>> queue = displays.get(supplier);
        if (queue != null){
            //return the first Display matching the class
            for (Display<?> display : queue) {
                if (ClassReflection.isInstance(cls,display)){
                    //noinspection unchecked
                    return (D) display;
                }
            }
            //if Display with matching class not found
            try {
                //create new Display
                Constructor c = ClassReflection.getConstructor(cls);
                Object o = c.newInstance();
                //noinspection unchecked
                D t = (D) o;
                t.setFeatureSupplier(supplier);
                //add to displays map
                queue.add(t);
                //add to featureCache
                F f = getFeature(supplier);
                if (f != null){
                    Array<Display<?>> q = featureCache.get(f);
                    if (q == null){
                        featureCache.put(f,new Array<>(t));
                    }
                    else {
                        q.add(t);
                    }
                }
                Game.getInstance().getLogger().debug("Created "+t.getClass().getSimpleName());
                t.init();
                return t;
            } catch (ReflectionException e) {
                throw new RuntimeException(e);
            }
        }
        //if not found
        try {
            Constructor c = ClassReflection.getConstructor(cls);
            Object o = c.newInstance();
            //noinspection unchecked
            D t = (D) o;
            t.setFeatureSupplier(supplier);
            //add to displays map
            Array<Display<?>> queue1 = new Array<>(t);
            displays.put(supplier,queue1);
            //add to featureCache
            F f = getFeature(supplier);
            if (f != null){
                Array<Display<?>> q = featureCache.get(f);
                if (q == null){
                    featureCache.put(f,new Array<>(t));
                }
                else {
                    q.add(t);
                }
            }
            Game.getInstance().getLogger().debug("Created "+t.getClass().getSimpleName());
            t.init();
            return t;
        } catch (ReflectionException e) {
            throw new RuntimeException(e);
        }
    }
    public <F extends Feature, D extends Actor & Display<F>> Optional<D> getOrNull(F feature, Class<D> cls){
        Array<Display<?>> queue = featureCache.get(feature);
        if (queue != null){
            //cache hit
            Display<?> toRemove = null;
            for (Display<?> display : queue) {
                if (ClassReflection.isInstance(cls,display)){
                    Feature feature1 = display.getFeature();
                    if (feature1.equals(feature)){
                        if (toRemove != null) queue.removeValue(toRemove,true);
                        //noinspection unchecked
                        return Optional.of((D) display);
                    }
                    else {
                        toRemove = display;
                    }
                }
            }
            queue.removeValue(toRemove,true);
        }
        //cache miss, search from displays
        for (ObjectMap.Entry<Supplier<?>, Array<Display<?>>> entry : displays) {
            if (Objects.equals(getFeature(entry.key), feature)){
                //Supplier which provides the desired feature is found
                for (Display<?> display : entry.value) {
                    if (ClassReflection.isInstance(cls,display)){
                        //Display with matching class is found
                        //noinspection unchecked
                        D d = (D) display;
                        //add it to cache
                        Array<Display<?>> queue1 = new Array<>(d);
                        featureCache.put(feature,queue1);
                        return Optional.of(d);
                    }
                }

            }
        }
        return Optional.empty();
    }
    private  <F> F getFeature(Supplier<F> supplier){
        try {
            return supplier.get();
        } catch (Exception e){
            return null;
        }
    }
    /**
     * Grantees all returned displays are/were registered under the provided feature.
     * Does NOT grantee all displays are there
     */
    public <F extends Feature, D extends Actor & Display<F>> Array<D> getAll(F feature){
        //Dirty works
        //noinspection unchecked,rawtypes
        return (Array) featureCache.get(feature);
    }
    public void remove(Feature feature){
        Array<DisplayEntry<?,?>> toRemove = new Array<>();
        for (DisplayEntry<?, ?> entry : displays) {
            Feature f = entry.supplier.get();
            if (f != null && f.equals(feature)){
                toRemove.add(entry);
            }
        }
        displays.removeAll(toRemove,false);
    }
    private <F extends Feature, D extends Actor & Display<F>> void addNewDisplay(Supplier<F> supplier, D display){
        displays.get(supplier)
    }

    private <F extends Feature, D extends Actor & Display<F>> D getEntry(F feature, Class<D> type){
        if (feature == null) return null;
        for (DisplayEntry<?, ?> entry : displays) {
            Feature f = entry.getFeature();
            if (f == null) continue;
            if (f.equals(feature)){
                Display<?> d = entry.getDisplay();
                if (d != null && ClassReflection.isInstance(type,d)){
                    //noinspection unchecked
                    return (D) d;
                }
            }
        }
        return null;
    }
}
