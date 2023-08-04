package me.carina.rpg.common.battle;

import me.carina.rpg.common.Array2DDisplay;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class TilesDisplay extends Array2DDisplay<Tile> {
    Tiles tiles;
    public TilesDisplay(Tiles tiles){
        this.tiles = tiles;
    }

    @Override
    public void tickMore() {

    }

    @Override
    public Tiles getFeature() {
        return tiles;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }
}
