package me.carina.rpg.common.world;

public enum CollisionType {
    BLOCK, PLAYER, ENEMY;
    public boolean collideWith(CollisionType type){
        return type.equals(this);
    }
}
