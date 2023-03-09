package me.carina.rpg.common.unit;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.Map;

public class UnitSpriteDef {
    ObjectMap<UnitSpriteType,TypeDef> definitions = new ObjectMap<>();

    public static class TypeDef{
        int color;
        String id;
    }
}
