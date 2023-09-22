package me.carina.conveyor.common.block;

import me.carina.conveyor.common.ArrayFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.file.AssetGroup;

public class Blocks extends ArrayFeature<Block> {
    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }
}
