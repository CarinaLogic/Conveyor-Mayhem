package me.carina.rpg.common;

import java.util.function.Supplier;

public interface Display<T extends Feature> {
    T getFeature();
}
