package me.carina.rpg.common.battle;

import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Floor extends Feature {
    @Override
    public FloorDisplay newDisplay() {
        return new FloorDisplay(this);
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
        @Override
        public void initFeature(Feature feature) {
            //NOOP
        }
    }
}
