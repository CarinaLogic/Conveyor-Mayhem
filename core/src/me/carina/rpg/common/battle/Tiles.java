package me.carina.rpg.common.battle;

import me.carina.rpg.Game;
import me.carina.rpg.common.util.Array2D;
import me.carina.rpg.common.util.FeatureArray2D;

public class Tiles {
    transient BattleMap map;
    FeatureArray2D<Tile> tiles;
    public Tiles(){} //for json
    public Tiles(BattleMap map){
        this.map = map;
        //debug purposes
        tiles = new FeatureArray2D<>(map,10,10);
        tiles.fill(v -> Game.getInstance().getAssets().get("core","plains", Tile.class));
    }

    public int getWidth() {
        return tiles.getWidth();
    }
    public int getHeight(){
        return tiles.getHeight();
    }

    public FeatureArray2D<Tile> getTiles() {
        return tiles;
    }
}
