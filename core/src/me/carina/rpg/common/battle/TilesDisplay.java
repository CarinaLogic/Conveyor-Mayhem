package me.carina.rpg.common.battle;

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
