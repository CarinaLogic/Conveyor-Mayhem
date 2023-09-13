package me.carina.conveyor.common.item;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.Feature;

public class Item extends Feature {
    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.items;
    }

    @Override
    public void tick() {

    }

    public static class Def extends Feature.Def {
        @Override
        public void initFeature(Feature feature) {

        }
    }
}
