package me.carina.rpg.common.block;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Facing {
    public static boolean isValid(Vector3 vector3){
        boolean lx = MathUtils.floor(vector3.x) == vector3.x;
        boolean ly = MathUtils.floor(vector3.y) == vector3.y;
        boolean lz = MathUtils.floor(vector3.z) == vector3.z;
        if (lx) return !ly && !lz;
        return ly ^ lz;
    }
}
