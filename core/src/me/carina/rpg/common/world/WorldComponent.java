package me.carina.rpg.common.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import me.carina.rpg.common.file.JsonAssetProvider;

public abstract class WorldComponent{
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
        JsonAssetProvider<?> assetProvider = game.assets.get(id, JsonAssetProvider.class);
        if (assetProvider == null){
            game.logger.error("Could not find "+id);
            return null;
        }
        Object component = assetProvider.getAsset();
        if (type.isInstance(component)) return type.cast(component);
        game.logger.error("Json deserialization from "+id+" yielded type "+component.getClass().getSimpleName()+
                ", which does not fit the desired type "+type.getSimpleName());
        return null;
    }
}
