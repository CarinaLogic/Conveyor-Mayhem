package me.carina.conveyor.common.battle;

import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;

public class Tile extends Feature {
    public Tile(){} //for json

    @Override
    public Class<Def> getDefClass() {
        return Def.class;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.tiles;
    }

    @Override
    public void tick() {

    }

    public static class Def extends Feature.Def{
        @Override
        public void initFeature(Feature feature) {
            //NOOP
        }
    }
}
