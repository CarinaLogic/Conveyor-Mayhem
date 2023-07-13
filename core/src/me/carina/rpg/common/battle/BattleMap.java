package me.carina.rpg.common.battle;

import com.badlogic.gdx.utils.Null;
import me.carina.rpg.Game;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.unit.Units;
import me.carina.rpg.common.util.Array2D;

public class BattleMap extends Feature {
    Tiles tiles;
    Units units;
    public BattleMap() {
        tiles = new Tiles();
        units = new Units();
    }

    public int getWidth(){return tiles.getWidth();}
    public int getHeight(){return tiles.getHeight();}


    @Override
    public BattleMapDisplay newDisplay() {
        BattleMapDisplay d = new BattleMapDisplay(this);
        setDisplay(d);
        return d;
    }

    @Override
    public BattleMapDisplay getDisplay() {
        return (BattleMapDisplay) super.getDisplay();
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
        return getWidth();
    }

    @Override
    public float getDisplayHeight() {
        return getHeight();
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }
}
