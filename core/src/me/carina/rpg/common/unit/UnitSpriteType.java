package me.carina.rpg.common.unit;

public enum UnitSpriteType {
    head, eyes, hair, leftArm(45,true),
    rightArm(45,false), body, legs;
    final int defaultRotation; // 0 means straight downwards, goes up if you go clockwise
    final boolean invertRotation; // invert the rotation
    UnitSpriteType(){
        this(0,false);
    }
    UnitSpriteType(int defaultRotation, boolean invertRotation) {
        this.defaultRotation = defaultRotation;
        this.invertRotation = invertRotation;
    }
}
