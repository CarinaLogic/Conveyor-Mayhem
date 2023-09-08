package me.carina.rpg.common.battle;

import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.client.scenes.BaseScreen;
import me.carina.rpg.common.*;
import me.carina.rpg.common.faction.Factions;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.unit.Units;

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


    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }

}
