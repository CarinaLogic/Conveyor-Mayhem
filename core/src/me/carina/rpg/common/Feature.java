package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;
import me.carina.rpg.common.file.Path;
import me.carina.rpg.common.util.Array;

//Basic design around actual arrayFeature objects
//1. The parent object, which holds all the info processed by sever
//2. The Actor object, which holds rendering info, and renders itself
//3. The definition object, which is provided by assets in the form of json, which is used to construct parent
//   optional, should be simple
public abstract class Feature implements Identifiable, Defined, AssetGrouped, Disposable {
    transient Array<Actor> displays = new Array<>();
    Identifier id;
    public Feature(){} //for json
    public <T extends Actor> T newDisplay(Class<T> type){
        try {
            Constructor c = ClassReflection.getConstructor(type,this.getClass());
            Object o = c.newInstance(this);
            //noinspection unchecked
            T t = (T) o;
            displays.add(t);
            t.addAction(Actions.forever(Actions.run(() -> Game.getInstance().getContext().add(this))));
            return t;
        } catch (ReflectionException e) {
            throw new RuntimeException(e);
        }
    }

    public Array<Actor> getDisplays() {
        return displays;
    }

    public <T extends Actor> T getDisplay(Class<T> type){
        for (Actor display : displays) {
            if (ClassReflection.isInstance(type, display)){
                //noinspection unchecked
                return (T) display;
            }
        }
        return null;
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
    }

    public abstract void tick();
}
