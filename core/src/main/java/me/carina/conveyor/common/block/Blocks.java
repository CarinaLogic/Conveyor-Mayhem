package me.carina.conveyor.common.block;

import com.badlogic.gdx.math.Vector3;
import me.carina.conveyor.common.ArrayFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.file.AssetGroup;

public class Blocks extends ArrayFeature<Block> {
    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    public Block getBlock(Vector3 pos){
        for (Block block : this) {
            if (block.contains(pos)) return block;
        }
        return null;
    }
}
