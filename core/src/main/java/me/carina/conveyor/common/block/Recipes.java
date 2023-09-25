package me.carina.conveyor.common.block;

import me.carina.conveyor.common.ArrayFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.resource.Resource;
import me.carina.conveyor.common.util.Array;

public class Recipes extends ArrayFeature<Recipe> {

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    public Array<Resource> process(Block block){
        return null;
    }
}
