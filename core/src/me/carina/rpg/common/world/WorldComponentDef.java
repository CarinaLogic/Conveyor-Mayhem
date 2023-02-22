package me.carina.rpg.common.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public abstract class WorldComponentDef {
    WorldComponentData data = new WorldComponentData();
    float width;
    float height;
    float density;
    float friction;
    float restitution;
    CollisionType collisionType;
    BodyDef.BodyType bodyType;
    public WorldComponentDef(){} //for json init
    public static WorldComponentDef fromId(String id, AbstractGameInstance game){
        WorldComponentDef o = game.assets.get(id, WorldComponentDef.class);
        if (o == null){
            game.logger.error("Could not find "+id);
            return null;
        }
        o.data.id = id;
        return o;
    }
}
