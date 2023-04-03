package me.carina.rpg.common.map;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.file.AssetGroup;

public abstract class CompoundFeature extends Feature {
    static final Def def = new Def();
    public CompoundFeature(AbstractGameInstance game) {
        super(game, def);
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
        public void init(Feature that) {
            //NOOP
        }
    }
}
