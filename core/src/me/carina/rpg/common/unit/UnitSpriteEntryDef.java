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
    public static class ConnectionDef{
        UnitSpriteType type;
        boolean drawUnder;
        Array<Coords> positions;
    }
    public static class Coords{
        int x;
        int y;
    }
}
