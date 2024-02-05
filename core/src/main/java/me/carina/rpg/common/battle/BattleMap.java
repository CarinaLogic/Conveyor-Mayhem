package me.carina.rpg.common.battle;

import me.carina.rpg.common.*;
import me.carina.rpg.common.faction.Factions;
import me.carina.rpg.common.faction.TurnOrder;
import me.carina.rpg.common.file.AssetGroup;

public class BattleMap extends Feature {
    @ChildFeature
    public Tiles tiles;
    @ChildFeature
    public Factions factions;
    @ChildFeature
    public TurnOrder turnOrder;
    public BattleMap() {
        tiles = new Tiles();
        factions = new Factions();
        turnOrder = new TurnOrder();
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
