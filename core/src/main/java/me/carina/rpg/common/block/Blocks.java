package me.carina.rpg.common.block;

import com.badlogic.gdx.math.Vector3;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.util.Array;

public class Blocks extends ArrayFeature<Block> {
    public Array<ResourceFacing> facingItems = new Array<>();
    boolean updated;

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    public void addFacingItem(ResourceFlow item, Vector3 pos){
        if (Facing.isValid(pos)){
            Array<ResourceFacing> duplicates = facingItems.match(f -> f.direction.equals(pos));
            if (duplicates.isEmpty()){
                facingItems.add(new ResourceFacing(item));
            }
            else {
                facingItems.get(0).merge(item);
            }
        }
        else Game.getInstance().getLogger().error("Failed to register an item due to invalid facing " + item);
    }
    public ResourceFacing getFacingItem(Vector3 facing){
        return facingItems.firstMatch(f -> f.direction.equals(facing));
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
                block.tick();
                if (block.updated){
                    block.updated = false;
                    updated = true;
                }
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
