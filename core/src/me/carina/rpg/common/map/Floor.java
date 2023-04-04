package me.carina.rpg.common.map;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;

public class Floor extends Feature{
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
