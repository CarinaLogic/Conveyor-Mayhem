package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Disposable;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;
import me.carina.rpg.common.file.Path;

//Basic design around actual feature objects
//1. The parent object, which holds all the info processed by sever
//2. The Actor object, which holds rendering info, and renders itself
//3. The definition object, which is provided by assets in the form of json, which is used to construct parent
//   optional, should be simple
public abstract class Feature implements Identifiable, Defined, AssetGrouped, Disposable {
    transient Display display;
    Identifier id;
    public Feature(){} //for json

    /**
     * Binds new display to this object, discarding the old one
     * @return The display object
     */
    public abstract Display newDisplay();

    public void destroyDisplay(){
        if (this.display != null) this.display.addAction(Actions.removeActor());
    }

    public Display getDisplay() {
        return display;
    }

    public void remove(){
        this.display.remove();
    }
    public abstract float getDisplayX();
    public abstract float getDisplayY();

    public abstract float getDisplayWidth();
    public abstract float getDisplayHeight();
    @Override
    public void dispose() {
        destroyDisplay();
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

    public void setDisplay(Display display) {
        this.display = display;
    }

    public static abstract class Def implements Identifiable, Definition {
        Identifier id;
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
}
