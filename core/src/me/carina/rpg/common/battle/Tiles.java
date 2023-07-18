package me.carina.rpg.common.battle;

import me.carina.rpg.Game;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.util.Array2D;
import me.carina.rpg.common.util.FeatureArray2D;

public class Tiles {
    FeatureArray2D<Tile> tiles;
    public Tiles(){
        //debug purposes
        tiles = new TileArray(10,10);
        tiles.fill(v -> Game.getInstance().getAssets().get("core","plains", Tile.class));
    }
    public int getX(Tile tile){
        return tiles.getIdenticalX(tile);
    }
    public int getY(Tile tile){
        return tiles.getIdenticalY(tile);
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

    public static class TileArray extends FeatureArray2D<Tile>{
        public TileArray(){} //for json
        public TileArray(int width, int height){
            super(width, height);
        }
        @Override
        public Feature getParent() {
            return ((BattleScreen) ((Client) Game.getInstance()).getScreen()).getBattleMap();
        }
    }
}
