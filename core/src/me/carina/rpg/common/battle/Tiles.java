package me.carina.rpg.common.battle;

import me.carina.rpg.Game;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.util.Array2D;
import me.carina.rpg.common.util.FeatureArray2D;

public class Tiles extends Array2DFeature<Tile>{

    public Tiles(){}

    @Override
    public TilesDisplay newDisplay() {
        return new TilesDisplay(this);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick(Context context) {
        if (getArray().get(0,0) == null){
            getArray().resize(10,10);
            getArray().fill(v -> Game.getInstance().getAssets().get("core","plains", Tile.class));
        }
    }
    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }
}
