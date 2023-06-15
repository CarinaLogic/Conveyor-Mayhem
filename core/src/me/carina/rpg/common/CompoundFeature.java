package me.carina.rpg.common;

import me.carina.rpg.common.file.AssetGroup;

public abstract class CompoundFeature extends Feature {
    static final Def def = new Def();
    public CompoundFeature(){} //for json

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
