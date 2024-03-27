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
    OrderedMap<Feature, Array<Supplier<?>>> featureCache = new OrderedMap<>();
    int LRUCounter = 0;
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
                    Array<Supplier<?>> q = featureCache.get(f);
                    if (q == null){
                        featureCache.put(f,new Array<>(supplier));
                    }
                    else {
                        q.add(supplier);
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
                Array<Supplier<?>> q = featureCache.get(f);
                if (q == null){
                    featureCache.put(f,new Array<>(supplier));
                }
                else {
                    q.add(supplier);
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
        if (feature == null) return Optional.empty();
        Array<Supplier<?>> queue = featureCache.get(feature);
        if (queue != null){
            //cache hit
            Supplier<?> supplierToRemove = null;
            for (Supplier<?> supplier : queue) {
                if (Objects.equals(getFeature(supplier), feature)) {
                    Display<?> toRemove = null;
                    Array<Display<?>> displays1 = displays.get(supplier);
                    for (Display<?> display : displays1) {
                        if (ClassReflection.isInstance(cls, display)) {
                            Feature feature1 = display.getFeature();
                            if (feature1.equals(feature)) {
                                if (supplierToRemove != null) queue.removeValue(supplierToRemove,true);
                                if (toRemove != null) displays1.removeValue(toRemove, true);
                                LRUReset(feature);
                                //noinspection unchecked
                                return Optional.of((D) display);
                            } else {
                                toRemove = display;
                            }
                        }
                    }
                    if (toRemove != null) displays1.removeValue(toRemove,true);
                }
                else {
                    supplierToRemove = supplier;
                }
            }
            if (supplierToRemove != null) queue.removeValue(supplierToRemove,true);
        }
        //cache miss, search from displays
        Array<Supplier<?>> newArray = new Array<>();
        for (ObjectMap.Entry<Supplier<?>, Array<Display<?>>> entry : displays) {
            if (Objects.equals(getFeature(entry.key), feature)){
                //Supplier which provides the desired feature is found
                if (newArray.isEmpty()) featureCache.put(feature,newArray);
                newArray.add(entry.key);
                for (Display<?> display : entry.value) {
                    if (ClassReflection.isInstance(cls,display)){
                        //Display with matching class is found
                        //noinspection unchecked
                        D d = (D) display;
                        return Optional.of(d);
                    }
                }

            }
        }
        return Optional.empty();
    }
    public void tick(){
        if (LRUCounter == 4){
            remove(featureCache.orderedKeys().get(0));
            LRUCounter = 0;
        }
        LRUCounter++;
    }
    private  <F> F getFeature(Supplier<F> supplier){
        try {
            return supplier.get();
        } catch (Exception e){
            return null;
        }
    }
    private void LRUReset(Feature feature){
        com.badlogic.gdx.utils.Array<Feature> keys = featureCache.orderedKeys();
        if (keys.get(0).equals(feature)){
            keys.removeIndex(0);
            keys.add(feature);
            LRUCounter = 0;
        }
    }
    /**
     * Grantees all returned displays are/were registered under the provided feature.
     * Does NOT grantee all displays are there
     */
    public <F extends Feature, D extends Actor & Display<F>> Array<D> getAll(F feature){
        Array<Supplier<?>> suppliers = featureCache.get(feature);
        if (suppliers.size == 0) return new Array<>();
        //Dirty works
        if (suppliers.size == 1) {
            //noinspection unchecked,rawtypes
            return (Array) displays.get(suppliers.get(0));
        }
        Array<D> a = new Array<>();
        for (Supplier<?> supplier : suppliers) {
            for (Display<?> display : displays.get(supplier)) {
                //noinspection unchecked
                a.add((D) display);
            }
        }
        return a;
    }
    public void remove(Feature feature){
        for (Supplier<?> supplier : featureCache.get(feature)) {
            if (Objects.equals(getFeature(supplier), feature)){
                Object f = getFeature(supplier);
                if (f != null) Game.getInstance().getLogger().debug("Removed "+f.getClass().getSimpleName());
                displays.remove(supplier);
            }
        }
        featureCache.remove(feature);
    }
}
