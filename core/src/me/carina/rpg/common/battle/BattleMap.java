package me.carina.rpg.common.battle;

import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.client.scenes.BaseScreen;
import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.unit.Units;

public class BattleMap extends Feature {
    public Tiles tiles;
    public Units units;
    public BattleMap() {
        tiles = new Tiles();
        units = new Units();
    }

    public int getWidth(){return tiles.getWidth();}
    public int getHeight(){return tiles.getHeight();}

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {
        tiles.contextAndTick();
        units.contextAndTick();
    }

    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }

}
