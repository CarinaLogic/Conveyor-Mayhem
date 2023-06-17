package me.carina.rpg.common.battle;

import com.badlogic.gdx.utils.Null;
import me.carina.rpg.Game;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.CompoundFeature;
import me.carina.rpg.common.util.Array2D;

public class BattleMap extends CompoundFeature {
    Tiles tiles;
    public BattleMap() {
        tiles = new Tiles();
    }

    public int getWidth(){return tiles.getWidth();}
    public int getHeight(){return tiles.getHeight();}


    @Override
    public BattleMapDisplay newDisplay() {
        BattleMapDisplay d = new BattleMapDisplay(this);
        setDisplay(d);
        return d;
    }
}
