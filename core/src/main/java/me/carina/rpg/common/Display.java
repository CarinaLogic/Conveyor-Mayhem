package me.carina.rpg.common;

import java.util.function.Supplier;

public interface Display<T extends Feature> {
    Supplier<T> getFeatureSupplier();
    void setFeatureSupplier();
    default T getFeature(){
        return getFeatureSupplier().get();
    }
}
