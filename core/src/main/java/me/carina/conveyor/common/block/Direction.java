package me.carina.conveyor.common.block;

import me.carina.conveyor.common.util.Array;

public enum Direction {
    plusX(1,0,0), plusZ(0,0,1), minusX(-1,0,0), minusZ(0,0,-1), up(0,1,0), down(0,-1,0);
    final int x, y, z;
    static final Array<Direction> horizontalRotationLoop = new Array<>(plusX,minusZ,minusX,plusZ);
    static final Array<Direction> rotationLoop = new Array<>(up, plusX, minusZ, down, minusX, plusZ);
    Direction(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
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
    public static Direction getByValue(int x, int y, int z){
        for (Direction value : Direction.values()) {
            if (value.x == x && value.y == y && value.z == z){
                return value;
            }
        }
        return null;
    }
}
