package me.carina.rpg.client.misc;

import com.badlogic.gdx.Gdx;

import java.util.function.Supplier;

public class IntervalSupplier<T> implements Supplier<T> {
    T value = null;
    Supplier<T> supplier;
    float cumTime = 0;
    float maxTime = 0;
    public IntervalSupplier(Supplier<T> supplier, float seconds){
        this.supplier = supplier;
        this.maxTime = seconds;
    }

    @Override
    public T get() {
        cumTime += Gdx.graphics.getDeltaTime();
        if (cumTime >= maxTime || value == null){
            cumTime -= maxTime;
            value = supplier.get();
        }
        return value;
    }
}
