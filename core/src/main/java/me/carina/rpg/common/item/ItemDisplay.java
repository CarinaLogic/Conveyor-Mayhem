package me.carina.rpg.common.item;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.ImageDisplay;

import java.util.function.Supplier;

public class ItemDisplay extends ImageDisplay implements Display<Item> {
    Supplier<Item> itemSupplier;
    public ItemDisplay(Item item){
    }

    @Override
    public float getDisplayX() {
        return 0;
    }

    @Override
    public float getDisplayY() {
        return 0;
    }

    @Override
    public float getDisplayWidth() {
        return 0;
    }

    @Override
    public float getDisplayHeight() {
        return 0;
    }

    @Override
    public Drawable getDrawable() {
        return null;
    }


    @Override
    public Supplier<Item> getFeatureSupplier() {
        return itemSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Item> supplier) {
        this.itemSupplier = supplier;
    }
}
