package me.carina.rpg.common.battle;

import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class TilesDisplay extends Display {
    Tiles tiles;
    public TilesDisplay(Tiles tiles){
        this.tiles = tiles;
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
        return getContext().get(BattleMap.class).getWidth();
    }

    @Override
    public float getDisplayHeight() {
        return getContext().get(BattleMap.class).getHeight();
    }

    @Override
    public Feature getFeature() {
        return tiles;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }
}
