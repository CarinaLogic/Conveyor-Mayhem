package me.carina.rpg.common.battle;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Tile extends Feature {
    int x;
    int y;
    public Tile(){} //for json
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public TileDisplay newDisplay() {
        TileDisplay d = new TileDisplay(this);
        setDisplay(d);
        return d;
    }

    @Override
    public Class<Def> getDefClass() {
        return Def.class;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.tiles;
    }


    public static class Def extends Feature.Def{
        @Override
        public void initFeature(Feature feature) {
            //NOOP
        }
    }
}
