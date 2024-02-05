package me.carina.rpg.common.battle;

import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Tile extends Feature {
    public Tile(){} //for json

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.tiles;
    }

    @Override
    public void tick() {

    }

    public static class Def extends Definition<Tile> {
        @Override
        public Class<Tile> getDefinedClass() {
            return Tile.class;
        }

        @Override
        public void init(Tile definedObject) {

        }
    }
}
