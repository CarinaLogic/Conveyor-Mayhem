package me.carina.conveyor.common.battle;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;

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
