package me.carina.rpg.common.item;

import me.carina.rpg.common.Definition;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.Feature;

public class Item extends Feature {

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.items;
    }

    @Override
    public void tick() {

    }

    public static class Def extends Definition<Item> {

        @Override
        public Class<Item> getDefinedClass() {
            return Item.class;
        }

        @Override
        public void init(Item definedObject) {

        }
    }
}
