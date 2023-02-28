package me.carina.rpg.common.unit;

import com.badlogic.gdx.utils.Array;

public class UnitSpriteEntryDef {
    UnitSpriteType type;
    String palette;
    int size;
    int defaultIndex;
    Array<Connection> centralConnections;
    Array<Connections> additionalConnections;
    public UnitSpriteEntryDef(){} //for json loading
    public static class Coords {
        int x;
        int y;
    }
    public static class Connections{
        UnitSpriteType type;
        Array<Coords> coords;
    }
    public static class Connection{
        UnitSpriteType type;
        int x;
        int y;
    }
}
