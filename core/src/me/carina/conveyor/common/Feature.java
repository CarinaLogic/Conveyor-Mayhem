package me.carina.conveyor.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.reflect.*;
import me.carina.conveyor.Game;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.file.Identifier;
import me.carina.conveyor.common.file.Path;
import me.carina.conveyor.common.util.Array;

//Basic design around actual arrayFeature objects
//1. The parent object, which holds all the info processed by sever
//2. The Actor object, which holds rendering info, and renders itself
//3. The definition object, which is provided by assets in the form of json, which is used to construct parent
//   optional, should be simple
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


    public static abstract class Def implements Identifiable, Definition {
        transient Identifier id;
        public abstract void initFeature(Feature feature);

        @Override
        public void init(Defined definedObject) {
            if (definedObject instanceof Feature) {
                Feature feature = (Feature) definedObject;
                feature.setId(id);
                initFeature(feature);
            }
        }

        @Override
        public Identifier getId() {
            return id;
        }

        @Override
        public void setId(Identifier id) {
            this.id = id;
        }
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
