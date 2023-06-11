package me.carina.rpg.common.world;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.file.AssetGroup;

public abstract class CompoundFeature extends Feature {
    static final Def def = new Def();
    public CompoundFeature(){} //for json
    public CompoundFeature(AbstractGameInstance game) {
        setGame(game);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public Class<? extends Feature.Def> getDefClass() {
        return Def.class;
    }

    public static class Def extends Feature.Def {

        @Override
        public void initFeature(Feature that) {
            //NOOP
        }
    }
}
