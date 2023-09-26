package me.carina.conveyor.common.resource;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;

public class Resource extends Feature {
    ResourceType type;

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }

    public ResourceType getType() {
        return type;
    }
}
