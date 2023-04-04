package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;
import me.carina.rpg.common.file.Path;

//Basic design around actual feature objects
//1. The parent object, which holds all the info processed by sever
//   should be able to convert to json, aside from game (which is initialized upon loading)
//2. The Actor object, which holds rendering info, and renders itself
//3. The definition object, which is provided by assets in the form of json, which is used to construct parent
//   optional, should be simple
public abstract class Feature implements GameObject, Identifiable, Defined {
    transient AbstractGameInstance game;
    Identifier id;
    public abstract Actor newActor();
    public AbstractGameInstance getGame(){
        return game;
    }
    @Override
    public void setGame(AbstractGameInstance game) {
        this.game = game;
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
