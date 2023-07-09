package me.carina.rpg.common.battle;

import me.carina.rpg.Game;
import me.carina.rpg.common.util.Array2D;
import me.carina.rpg.common.util.FeatureArray2D;

public class Tiles {
    FeatureArray2D<Tile> tiles;
    public Tiles(){
        //debug purposes
        tiles = new FeatureArray2D<>(10,10,
                tile -> {if (tile.hasDisplay()) tile.getDisplay().addActor(tile.newDisplay());},
                tile -> {if (tile.hasDisplay()) tile.getDisplay().removeActor(tile.newDisplay());}
        );
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
