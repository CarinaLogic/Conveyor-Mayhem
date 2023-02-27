package me.carina.rpg.common.unit;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ObjectMap;

public class UnitSpriteEntryDef {
    //One definition per one (potentially shared) palette
    String name;
    PaletteDef palette;
    ConnectionPaletteDef connection;
    Array<UnitSpriteTypeEntryDef> sprites;
    public UnitSpriteEntryDef(){} //for json loading
    public static class UnitSpriteTypeEntryDef{
        UnitSpriteType type;
        int width;
        int height;
        int originX;
        int originY;
        int offsetX;
        int offsetY;
        int spriteCount;
        public UnitSpriteTypeEntryDef(){} //for json loading
    }
    public static class PaletteDef{
        int colors;
        int entrySize;
        int originX;
        int originY;
        public PaletteDef(){}//for json loading
    }
    public static class ConnectionPaletteDef{
        int originX;
        int originY;
        int size;
        public ConnectionPaletteDef(){} //for json loading
    }
}
