package me.carina.rpg.common.map;

public interface AbstractFeatureProvider<T extends AbstractFeature> {
    T getFeature();
    void setFeature(T feature);
}
