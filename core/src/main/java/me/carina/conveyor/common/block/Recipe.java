package me.carina.conveyor.common.block;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.resource.Resource;
import me.carina.conveyor.common.util.Array;

public class Recipe extends Feature {
    Array<Resource> input;
    Array<Resource> output;
    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }

    public Array<Resource> process(Block block){
        for (IOProperty ioProperty : block.ioProperties) {

        }
    }
}
