package me.carina.rpg.common.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;

public interface WorldComponent{
    BodyDef.BodyType getBodyType();
    CollisionType getCollisionType();
    TextureRegion getTextureRegion();
    float getWidth();
    float getHeight();
    float getDensity();
    float getFriction();
    float getRestitution();
}
