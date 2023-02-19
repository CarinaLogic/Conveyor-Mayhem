package me.carina.rpg.common.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import me.carina.rpg.common.file.JsonAssetLoader;

public abstract class WorldComponent {
    String id;
    transient TextureRegion texture;
    transient AbstractGameInstance game;
    float width;
    float height;
    float density;
    float friction;
    float restitution;
    CollisionType collisionType;
    BodyDef.BodyType bodyType;
    public WorldComponent(){} //for json init
    public static <T extends WorldComponent> T fromId(String id, Class<T> type ,AbstractGameInstance game){
        Object o = game.assets.get(id, type);
        if (o == null){
            game.logger.error("Could not find "+id);
            return null;
        }
        if (type.isInstance(o)) return type.cast(o);
        game.logger.error("Json deserialization from "+id+" yielded type "+o.getClass().getSimpleName()+
                ", which does not fit the desired type "+type.getSimpleName());
        return null;
    }

    public TextureRegion getTexture() {
        if (texture == null) texture = game.getAssets().get(id, TextureRegion.class);
        return texture;
    }

}
