package me.carina.rpg.common.resource;

import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Resource extends Feature {
    ResourceType type;

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.resources;
    }

    @Override
    public void tick() {

    }

    public ResourceType getType() {
        return type;
    }
}
