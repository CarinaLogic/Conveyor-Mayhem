package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.Game;
import me.carina.rpg.client.battle.UIBattleStatPanel;
import me.carina.rpg.common.util.Array;

import java.util.Optional;
import java.util.function.Supplier;

public final class Displays {
    Array<DisplayEntry<?,?>> displays = new Array<>();

    /**
     * *WARNING: Use static Supplier object*
     */
    public <F extends Feature, D extends Actor & Display<F>> D get(Supplier<F> supplier, Class<D> cls){
        if (supplier == null) return null;
        F feature;
        try {
            feature = supplier.get();
        } catch (Exception e){
            feature = null;
        }
        if (feature == null){
            for (DisplayEntry<?, ?> entry : displays) {
                if (entry.supplier.equals(supplier)){
                    return (D) entry.display;
                }
            }
        }
        D dis = getEntry(feature, cls);
        if (dis != null) return dis;
        try {
            Constructor c = ClassReflection.getConstructor(cls);
            Object o = c.newInstance();
            //noinspection unchecked
            D t = (D) o;
            t.setFeatureSupplier(supplier);
            addNewDisplay(supplier,t);
            Game.getInstance().getLogger().debug("Created "+t.getClass().getSimpleName());
            t.init();
            return t;
        } catch (ReflectionException e) {
            throw new RuntimeException(e);
        }
    }
    public <F extends Feature, D extends Actor & Display<F>> D getOrNull(F feature, Class<D> cls){
        return getEntry(feature, cls);
    }
    public <F extends Feature, D extends Actor & Display<F>> Array<D> getAll(F feature){
        Array<D> toReturn = new Array<>();
        for (DisplayEntry<?, ?> entry : displays) {
            Feature f = entry.getFeature();
            if (f == null) continue;
            if (f.equals(feature)){
                Display<?> d = entry.getDisplay();
                if (d != null){
                    //noinspection unchecked
                    toReturn.add((D) d);
                }
            }
        }
        return toReturn;
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
        displays.add(new DisplayEntry<>(supplier,display));
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

    private static class DisplayEntry<F extends Feature, D extends Actor & Display<F>>{
        Supplier<F> supplier;
        D display;
        public DisplayEntry(Supplier<F> supplier, D display){
            this.supplier = supplier;
            this.display = display;
        }

        public D getDisplay() {
            return display;
        }

        public Supplier<F> getSupplier() {
            return supplier;
        }
        public F getFeature(){
            if (supplier == null) return null;
            F feature;
            try {
                feature = supplier.get();
                return feature;
            } catch (Exception e){
                return null;
            }
        }

        @Override
        public String toString() {
            return display.toString();
        }
    }
}
