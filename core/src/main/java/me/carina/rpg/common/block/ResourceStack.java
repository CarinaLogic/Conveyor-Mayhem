package me.carina.rpg.common.block;

import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.resource.Resource;

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
