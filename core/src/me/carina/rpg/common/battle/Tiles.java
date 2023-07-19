package me.carina.rpg.common.battle;

import me.carina.rpg.Game;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.util.Array2D;
import me.carina.rpg.common.util.FeatureArray2D;

public class Tiles extends Feature{
    @AutoDisplay
    public FeatureArray2D<Tile> tiles;
    public Tiles(){
        //debug purposes
        tiles = new FeatureArray2D<>(10,10);
    }

    @Override
    public Display newDisplay() {
        return new TilesDisplay(this);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick(Context context) {
        if (tiles.get(0,0) == null){
            tiles.fill(this, v -> Game.getInstance().getAssets().get("core","plains", Tile.class));
        }
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


    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }
}
