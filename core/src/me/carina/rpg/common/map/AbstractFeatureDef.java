package me.carina.rpg.common.map;

public abstract class AbstractFeatureDef<T extends AbstractFeature> {
    public abstract T toFeature();
}
