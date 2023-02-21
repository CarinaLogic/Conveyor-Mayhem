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
    public static <T extends WorldComponentDef> T fromId(String id, Class<T> type, AbstractGameInstance game){
        Object o = game.assets.get(id, type);
        if (o == null){
            game.logger.error("Could not find "+id);
            return null;
        }
        if (type.isInstance(o)){
            T t = type.cast(o);
            t.data.id = id;
            return t;
        }
        game.logger.error("Json deserialization from "+id+" yielded type "+o.getClass().getSimpleName()+
                ", which does not fit the desired type "+type.getSimpleName());
        return null;
    }
}
