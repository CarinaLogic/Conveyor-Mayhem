package me.carina.rpg.common.unit;

import com.badlogic.gdx.utils.Array;

public class UnitSpriteEntryDef {
    UnitSpriteType type;
    String palette;
    int size;
    Array<Connection> connections;
    public UnitSpriteEntryDef(){} //for json loading
    public static class Connection{
        UnitSpriteType type;
        int x;
        int y;
    }
}
