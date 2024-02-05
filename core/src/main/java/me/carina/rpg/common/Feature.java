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
    transient Array<Actor> displays = new Array<>();
    Identifier id;
    public Feature(){} //for json
    public <T extends Actor & Display<? extends Feature>> T newDisplay(Class<T> type){
        try {
            Constructor c = ClassReflection.getConstructor(type,this.getClass());
            Object o = c.newInstance(this);
            //noinspection unchecked
            T t = (T) o;
            removeDisplay(type);
            displays.add(t);
            return t;
        } catch (ReflectionException e) {
            throw new RuntimeException(e);
        }
    }

    public Array<Actor> getDisplays() {
        return displays;
    }

    public <T extends Actor & Display<? extends Feature>> T getDisplay(Class<T> type){
        for (Actor display : displays) {
            if (ClassReflection.isInstance(type, display)){
                //noinspection unchecked
                return (T) display;
            }
        }
        return newDisplay(type);
    }

    public <T extends Actor> void removeDisplay(Class<T> type){
        for (Actor display : displays) {
            if (ClassReflection.isInstance(type, display)){
                displays.remove(display);
            }
        }
    }

    public void remove(){
        for (Actor display : displays) {
            display.remove();
        }
    }
    @Override
    public void dispose() {
        for (Actor display : displays) {
            display.remove();
        }
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
