package me.carina.conveyor.common.block;

import me.carina.conveyor.common.ChildFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.resource.Resource;

public class ResourceFlow extends Feature {
    @ChildFeature
    Resource resource;
    int x,y,z;
    float flow;
    float maxFlow;


    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }

    public boolean isValid(Block block){
        return false;
    }
}
