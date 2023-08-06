package me.carina.rpg.common.battle;

import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.unit.Units;

public class BattleMap extends Feature {
    @AutoDisplay
    public Units units;
    @AutoDisplay
    public Tiles tiles;
    public BattleMap() {
        tiles = new Tiles();
        units = new Units();
    }

    public int getWidth(){return tiles.getWidth();}
    public int getHeight(){return tiles.getHeight();}


    @Override
    protected BattleMapDisplay newDisplay() {
        BattleMapDisplay d = new BattleMapDisplay(this);
        setDisplay(d);
        return d;
    }

    @Override
    public BattleMapDisplay getDisplay() {
        return (BattleMapDisplay) super.getDisplay();
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick(Context context) {

    }

    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }
}
