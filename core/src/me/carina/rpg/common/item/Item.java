package me.carina.rpg.common.item;

import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.Feature;

public class Item extends Feature {
    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }

    @Override
    public Display newDisplay() {
        ItemDisplay d = new ItemDisplay(this);
        setDisplay(d);
        return d;
    }

    @Override
    public float getDisplayX() {
        return 0;
    }

    @Override
    public float getDisplayY() {
        return 0;
    }

    @Override
    public float getDisplayWidth() {
        return 0;
    }

    @Override
    public float getDisplayHeight() {
        return 0;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.items;
    }

    public static class Def extends Feature.Def {
        @Override
        public void initFeature(Feature feature) {

        }
    }
}
