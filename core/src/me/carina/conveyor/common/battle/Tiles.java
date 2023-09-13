package me.carina.conveyor.common.battle;

import me.carina.conveyor.Game;
import me.carina.conveyor.common.*;
import me.carina.conveyor.common.file.AssetGroup;

public class Tiles extends Array2DFeature<Tile>{

    public Tiles(){}

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {
        if (getArray().get(0,0) == null){
            getArray().resize(10,10);
            getArray().fill(v -> Game.getInstance().getAssets().get("core","plains", Tile.class));
        }
        super.tick();
    }
    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }
}
