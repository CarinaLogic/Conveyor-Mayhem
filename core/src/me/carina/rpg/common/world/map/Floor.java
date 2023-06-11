package me.carina.rpg.common.world.map;

import me.carina.rpg.common.world.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Floor extends Feature {
    boolean passable;


    @Override
    public FloorActor newActor() {
        return new FloorActor(this);
    }

    @Override
    public Class<Def> getDefClass() {
        return Def.class;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.floors;
    }


    public static class Def extends Feature.Def{
        boolean passable;

        @Override
        public void initFeature(Feature feature) {
            if (feature instanceof Floor) {
                Floor that = (Floor) feature;
                that.passable = passable;
            }
        }
    }
}
