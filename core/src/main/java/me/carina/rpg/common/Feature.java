package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.reflect.*;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;
import me.carina.rpg.common.file.Path;
import me.carina.rpg.common.util.Array;

/**
 * Abstract class for Feature.
 * <p>
 * Feature is treated like a node on a tree, whose children is all non-transient field.
 * Feature must follow these requirements.
 * <ul>
 * <li> All game components that must be communicated between server and client must implement Feature and be json serializable,
 * meaning all non-transient fields should not directly reference parent object nor object on another branch (aka no closed loop).
 * <li>Feature must implement tick() which is executed on server, and execute tick() on appropriate children. Children annotated with @ChildFeature is ticked automatically.
 * For that reason, it's preferred to make a Feature's parent always a Feature.
 * </ul>
 * Feature can have multiple displays. If the feature is destroyed, the associated displays will also be destroyed.
 */
public abstract class Feature implements Identifiable, Defined, AssetGrouped, Disposable {
    Identifier id;
    public Feature(){} //for json

    public void remove(){
        Game.getClient().getDisplays().remove(this);
    }
    @Override
    public void dispose() {
        Game.getClient().getDisplays().remove(this);
    }

    public abstract AssetGroup getAssetGroup();

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public void setId(Identifier id) {
        this.id = id;
    }

    public Path getPath(){
        return getId().toPath(getAssetGroup());
    }

    public void contextAndTick(){
        Game.getInstance().getContext().add(this);
        tick();
        Field[] fields = ClassReflection.getDeclaredFields(getClass());
        for (Field field : fields) {
            if (field.isAnnotationPresent(ChildFeature.class)){
                try {
                    Object o = field.get(this);
                    if (o instanceof Feature){
                        ((Feature) o).contextAndTick();
                    }
                } catch (ReflectionException ignored) {}
            }
        }
    }

    public abstract void tick();
}
