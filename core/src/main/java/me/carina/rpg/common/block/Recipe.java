package me.carina.rpg.common.block;

import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.util.Array;

public class Recipe extends Feature {
    Array<ResourceStack> input;
    Array<ResourceStack> output;

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }


}
