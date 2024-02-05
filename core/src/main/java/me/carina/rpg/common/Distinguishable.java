package me.carina.rpg.common;

import java.util.Objects;

public interface Distinguishable {
    String getKey();
    default boolean equalsKey(String key){
        return Objects.equals(key,getKey());
    }
}
