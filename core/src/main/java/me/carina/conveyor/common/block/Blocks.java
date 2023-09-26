package me.carina.conveyor.common.block;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ObjectMap;
import me.carina.conveyor.Game;
import me.carina.conveyor.common.ArrayFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.util.Map;

public class Blocks extends ArrayFeature<Block> {
    public Map<Vector3,ResourceStack> facingItems = new Map<>();
    boolean updated;

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    public void addFacingItem(Vector3 facing, ResourceStack item){
        if (Facing.isValid(facing)){
            facingItems.put(facing.cpy(),item);
        }
        else Game.getInstance().getLogger().error("Failed to register an item due to invalid Facing "+facing);
    }
    public ResourceStack getFacingItem(Vector3 facing){
        return facingItems.get(facing);
    }
    public void reset(){
        facingItems.clear();
    }

    @Override
    public void tick() {
        reset();
        updated = true;
        while (updated){
            updated = false;
            for (Block block : this) {
                if (block.update()) updated = true;
            }
        }
    }

    public Block getBlock(Vector3 pos){
        for (Block block : this) {
            if (block.contains(pos)) return block;
        }
        return null;
    }
}
