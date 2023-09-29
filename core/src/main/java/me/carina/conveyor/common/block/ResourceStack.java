package me.carina.conveyor.common.block;

import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.resource.Resource;

public class ResourceStack extends Feature {
    Resource resource;
    float count;
    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }

    public Resource getResource() {
        return resource;
    }

    public float getCount() {
        return count;
    }
}
