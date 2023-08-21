package me.carina.rpg.common.item;

import me.carina.rpg.common.Context;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.Feature;

public class Item extends Feature {
    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }

    @Override
    protected Display newDisplay() {
        ItemDisplay d = new ItemDisplay(this);
        setDisplays(d);
        return d;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.items;
    }

    @Override
    public void tick(Context context) {

    }

    public static class Def extends Feature.Def {
        @Override
        public void initFeature(Feature feature) {

        }
    }
}
