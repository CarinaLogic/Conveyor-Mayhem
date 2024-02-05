package me.carina.rpg.common.item;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.ImageDisplay;

public class ItemDisplay extends ImageDisplay implements Display<Item> {
    Item item;
    public ItemDisplay(Item item){
        this.item = item;
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
    public Item getFeature() {
        return item;
    }
}
