package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Disposable;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;
import me.carina.rpg.common.file.Path;

//Basic design around actual feature objects
//1. The parent object, which holds all the info processed by sever
//   should be able to convert to json, aside from game (which is initialized upon loading)
//2. The Actor object, which holds rendering info, and renders itself
//3. The definition object, which is provided by assets in the form of json, which is used to construct parent
//   optional, should be simple
public abstract class Feature implements GameObject, Identifiable, Defined, AssetGrouped, Disposable {
    transient AbstractGameInstance game;
    transient Display display;
    Identifier id;
    public Feature(){} //for json
    public abstract Display newDisplay();
    public void bindDisplay(){
        this.display = newDisplay();
    }
    public void destroyDisplay(){
        if (this.display != null) this.display.addAction(Actions.removeActor());
    }
    public AbstractGameInstance getGame(){
        return game;
    }
    @Override
    public Feature setGame(AbstractGameInstance game) {
        this.game = game;
        return this;
    }

    @Override
    public void dispose() {
        if (game instanceof Client) {
            destroyDisplay();
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
