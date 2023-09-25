package me.carina.conveyor.common.battle;

import me.carina.conveyor.common.*;
import me.carina.conveyor.common.faction.Factions;
import me.carina.conveyor.common.file.AssetGroup;

public class BattleMap extends Feature {
    @ChildFeature
    public Tiles tiles;
    @ChildFeature
    public Factions factions;
    public BattleMap() {
        tiles = new Tiles();
        factions = new Factions();
    }

    public int getWidth(){return tiles.getWidth();}
    public int getHeight(){return tiles.getHeight();}

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }


}
