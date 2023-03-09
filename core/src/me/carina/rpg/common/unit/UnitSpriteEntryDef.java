package me.carina.rpg.common.unit;

import com.badlogic.gdx.utils.Array;

public class UnitSpriteEntryDef {
    UnitSpriteType type;
    Array<String> palettes;
    int width;
    int height;
    int rotationLoop; //0 if no loop
    Coords connectionPos;
    Array<ConnectionDef> connections;
    public UnitSpriteEntryDef(){} //for json loading
    public static class ConnectionDef{
        UnitSpriteType type;
        boolean drawUnder;
        Array<Coords> positions;
        public ConnectionDef(){} //for json loading
    }
    public static class Coords{
        int x;
        int y;
        public Coords(){} //for json loading
    }
}
