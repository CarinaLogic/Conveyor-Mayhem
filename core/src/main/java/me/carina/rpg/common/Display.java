package me.carina.rpg.common;

import java.util.function.Supplier;

public interface Display<T extends Feature> {
    Supplier<T> getFeatureSupplier();
    void setFeatureSupplier(Supplier<T> supplier);
    default T getFeature(){
        try{
            return getFeatureSupplier().get();
        } catch (Exception e){
            return null;
        }
    }
}
