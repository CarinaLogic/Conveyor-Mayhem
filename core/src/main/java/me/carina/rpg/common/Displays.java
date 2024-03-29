package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.Game;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.Map;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This class handle registrations of Display & finding associated Display object from Feature.
 * Display will be registered under conditions described below.
 * <ul>
 * <li>For every instances of Feature, it can only have one Display of the same Display class.
 * <li>Different Display class for same Feature instance can coexist.
 * <li>Feature will be provided under Supplier class. When supplied feature changes, associated display will not change.
 * <li>Display's featureSupplier should only be modified upon registration, never be overwritten with another instance
 * </ul>
 */
public final class Displays {
    //TODO fix performance
    Map<Supplier<?>, Array<Display<?>>> displays = new Map<>();
    OrderedMap<Feature, Array<Supplier<?>>> featureCache = new OrderedMap<>();
    int LRUCounter = 0;
    /**
     * Search for Display matching Feature (provided by Supplier) and class.
     * If none exists, create new display & register it with provided supplier.
     * If supplier returns null or throws exception, new instance will always be created unless there's an existing Display with the supplier of same instance
     * <p>
     * High overhead for registration, low overhead for searching.
     * Never evaluates Display's supplier & always assumes Display's supplier is not overwritten for performance.
     */
    public <F extends Feature, D extends Actor & Display<F>> D get(Supplier<F> supplier, Class<D> cls){
        //if supplier itself is null, return
        if (supplier == null) return null;
        //Step 1
        //if supplier is the same instance as the registered one (guaranteed to return same Feature upon evaluation)
        Array<Display<?>> displaysQueue = displays.get(supplier);
        if (displaysQueue != null){
            //return the first Display matching the class
            for (Display<?> display : displaysQueue) {
                if (ClassReflection.isInstance(cls,display)){
                    //noinspection unchecked
                    return (D) display;
                }
            }
        }
        //Step 2
        //if supplier with same instance not found
        //or if supplier with the same instance does not contain desired Display (The Display can still be registered under different supplier retuning same Feature)
        //search from featureCache
        F feature = getFeature(supplier);
        //Feature has to be nonnull to have an entry on cache
        if (feature != null) {
            Array<Supplier<?>> featureCacheQueue = featureCache.get(feature);
            if (featureCacheQueue != null) {
                //cache hit
                //if cache contains suppliers that no longer returns desired Feature, put it here to be removed
                Supplier<?> supplierToRemove = null;
                for (Supplier<?> cachedSupplier : featureCacheQueue) {
                    if (Objects.equals(getFeature(cachedSupplier), feature)) {
                        //supplier returns the desired Feature
                        Array<Display<?>> displays1 = displays.get(cachedSupplier);
                        for (Display<?> display : displays1) {
                            if (ClassReflection.isInstance(cls, display)) {
                                //Display's class matches, return it
                                if (supplierToRemove != null) featureCacheQueue.removeValue(supplierToRemove, true);
                                LRUReset(feature);
                                //noinspection unchecked
                                return (D) display;
                            }
                        }
                    } else {
                        //supplier no longer returns that Feature, remove it from cache
                        supplierToRemove = cachedSupplier;
                    }
                }
            }

            //Step 3
            //Cache misses, perform full scan for displays
            if (featureCacheQueue == null) featureCacheQueue = new Array<>();
            for (ObjectMap.Entry<Supplier<?>, Array<Display<?>>> entry : displays) {
                if (Objects.equals(getFeature(entry.key), feature)) {
                    //Supplier which provides the desired feature is found
                    //create new entry for cache if it has not created yet
                    if (featureCache.isEmpty()) featureCache.put(feature, featureCacheQueue);
                    //add it to cache
                    featureCacheQueue.add(entry.key);
                    for (Display<?> display : entry.value) {
                        if (ClassReflection.isInstance(cls, display)) {
                            //Display with matching class is found
                            LRUReset(feature);
                            //noinspection unchecked
                            return (D) display;
                        }
                    }

                }
            }
        }
        //Step 4
        //No entry for displays, register new Display instance
        try {
            Constructor c = ClassReflection.getConstructor(cls);
            Object o = c.newInstance();
            //noinspection unchecked
            D t = (D) o;
            t.setFeatureSupplier(supplier);
            //add to displays map
            Array<Display<?>> queue1 = new Array<>(t);
            displays.put(supplier, queue1);
            //add to featureCache
            F f = getFeature(supplier);
            if (f != null) {
                Array<Supplier<?>> q = featureCache.get(f);
                if (q == null) {
                    featureCache.put(f, new Array<>(supplier));
                } else {
                    q.add(supplier);
                }
            }
            Game.getInstance().getLogger().debug("Created " + t.getClass().getSimpleName());
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
            //if feature at featureCache index 0 is not used for 4 ticks, remove from cache
            featureCache.remove(featureCache.orderedKeys().get(0));
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
        //least recent key should at index 0
        //if it gets used, reset the counter & move the entry to tail
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
        //search from cache
        Array<Supplier<?>> featureCacheQueue = featureCache.get(feature);
        if (featureCacheQueue != null) {
            //cache hit
            for (Supplier<?> cachedSupplier : featureCacheQueue) {
                if (Objects.equals(getFeature(cachedSupplier), feature)) {
                    //supplier returns the Feature to remove, remove it
                    displays.remove(cachedSupplier);
                }
            }
        }
        //remove cache
        featureCache.remove(feature);
        //perform full scan for displays
        for (ObjectMap.Entry<Supplier<?>, Array<Display<?>>> entry : displays) {
            if (Objects.equals(getFeature(entry.key), feature)){
                //Supplier which provides the feature to remove is found
                displays.remove(entry.key);
            }
        }
    }
}
