package me.carina.conveyor.common.block;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import me.carina.conveyor.common.util.Array;

public enum Direction {
    plusX(0), plusZ(1), minusX(2), minusZ(3);
    final int dir;
    static final Array<Direction> horizontalRotationLoop = new Array<>(plusX,minusZ,minusX,plusZ);
    static final Array<Direction> rotationLoop = new Array<>(plusX, minusZ, minusX, plusZ);
    Direction(int dir){
        this.dir = dir;
    }
    public Direction rotateHorizontal(boolean inverted){
        int i = horizontalRotationLoop.indexOf(this,false);
        if (i == -1) return this;
        if (inverted){
            i = (i - 1 + horizontalRotationLoop.size) % horizontalRotationLoop.size;
        }
        else {
            i = (i + 1) % horizontalRotationLoop.size;
        }
        return horizontalRotationLoop.get(i);
    }
    public Direction rotate(boolean inverted){
        int i = rotationLoop.indexOf(this,false);
        if (i == -1) return this;
        if (inverted){
            i = (i - 1 + rotationLoop.size) % rotationLoop.size;
        }
        else {
            i = (i + 1) % rotationLoop.size;
        }
        return rotationLoop.get(i);
    }
    public Vector3 transform(Vector3 pos, Vector3 size){
        switch (dir) {
            case 1: return new Vector3(size.z-pos.z,pos.y,pos.x);
            case 2: return new Vector3(size.x-pos.x,pos.y,size.z-pos.z);
            case 3: return new Vector3(pos.z,pos.y,size.x-pos.x);
            default: return pos.cpy();
        }
    }
}
