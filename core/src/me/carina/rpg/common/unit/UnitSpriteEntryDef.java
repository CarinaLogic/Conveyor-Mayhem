package me.carina.rpg.common.unit;

import com.badlogic.gdx.utils.Array;

public class UnitSpriteEntryDef {
    //One definition per one (potentially shared) palette
    String name;
    PaletteDef palette;
    Array<UnitSpriteTypeEntryDef> sprites;
    public UnitSpriteEntryDef(){} //for json loading
    public static class UnitSpriteTypeEntryDef{
        UnitSpriteType type;
        int size;
        int width;
        int height;
        int originX;
        int originY;
        Array<IndividualSpriteData> data;
        public UnitSpriteTypeEntryDef(){} //for json loading
    }
    public static class IndividualSpriteData {
        Array<ConnectionInfo> connections;
        int rotation; //in degrees
    }
    public static class PaletteDef{
        int colors;
        int entrySize;
        int originX;
        int originY;
        public PaletteDef(){}//for json loading
    }
    public static class ConnectionInfo{
        UnitSpriteType type;
        int x; //Absolute coords, count from very top left of the png
        int y; //You probably want coords between pixels, just pick the coords
               // of the pixel whose top left corner is touching the desired coords
        public ConnectionInfo(){}//for json loading
    }
}
