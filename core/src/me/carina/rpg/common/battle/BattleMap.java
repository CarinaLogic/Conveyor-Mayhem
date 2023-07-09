package me.carina.rpg.common.battle;

import com.badlogic.gdx.utils.Null;
import me.carina.rpg.Game;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.CompoundFeature;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.Units;
import me.carina.rpg.common.util.Array2D;

public class BattleMap extends CompoundFeature {
    Tiles tiles;
    Units units;
    public BattleMap() {
        tiles = new Tiles();
        units = new Units(this);
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
}
