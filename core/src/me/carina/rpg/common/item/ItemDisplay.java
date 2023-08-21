package me.carina.rpg.common.item;

import me.carina.rpg.common.Feature;

public class ItemDisplay extends Display {
    Item item;
    public ItemDisplay(Item item){
        this.item = item;
    }

    @Override
    public void tick() {

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
    public Feature getFeature() {
        return item;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }
}
