package me.carina.rpg.common.block;

import me.carina.rpg.common.ChildFeature;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.command.DataRange;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.resource.Resource;

public class ResourceFlow extends Feature {
    @ChildFeature
    Resource resource;
    float flow;
    DataRange size;
    DataRange heat;


    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }

}
