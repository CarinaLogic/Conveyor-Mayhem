package me.carina.rpg.common.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Block implements WorldComponent{

    TextureRegion region = new TextureRegion(new Texture(Gdx.files.internal("testBlock.png")));

    @Override
    public BodyDef.BodyType getBodyType() {
        return BodyDef.BodyType.StaticBody;
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.BLOCK;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return region;
    }

    @Override
    public float getWidth() {
        return 1;
    }

    @Override
    public float getHeight() {
        return 1;
    }

    @Override
    public float getDensity() {
        return 0.8f;
    }

    @Override
    public float getFriction() {
        return 0.5f;
    }

    @Override
    public float getRestitution() {
        return 0;
    }
}
