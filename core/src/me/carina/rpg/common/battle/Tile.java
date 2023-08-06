package me.carina.rpg.common.battle;

import me.carina.rpg.common.Context;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Tile extends Feature {
    public Tile(){} //for json
    @Override
    protected TileDisplay newDisplay() {
        TileDisplay d = new TileDisplay(this);
        setDisplay(d);
        return d;
    }

    @Override
    public Class<Def> getDefClass() {
        return Def.class;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.tiles;
    }

    @Override
    public void tick(Context context) {
        //NOOP
    }

    public static class Def extends Feature.Def{
        @Override
        public void initFeature(Feature feature) {
            //NOOP
        }
    }
}
