package me.carina.rpg.common.block;

import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.resource.Resource;
import me.carina.rpg.common.util.Array;

public class Recipes extends ArrayFeature<Recipe> {

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    public Array<Resource> process(Block block){
        return null;
    }
}
