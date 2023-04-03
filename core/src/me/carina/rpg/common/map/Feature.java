package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.GameObject;
import me.carina.rpg.common.Identifiable;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;
import me.carina.rpg.common.file.Path;

//Basic design around actual feature objects
//1. The parent object, which holds all the info processed by sever
//   should be able to convert to json, aside from game (which is initialized upon loading)
//2. The Actor object, which holds rendering info, and renders itself
//3. The definition object, which is provided by assets in the form of json, which is used to construct parent
//   optional, should be simple
public abstract class Feature implements GameObject, Identifiable {
    transient AbstractGameInstance game;
    Identifier id;
    public Feature(AbstractGameInstance game, Def def){
        this.game = game;
        def.initFeature(this);
    }
    public Feature(AbstractGameInstance game, Identifier id){
        this.game = game;
        Def def = game.getAssets().get(id.toPath(getAssetGroup()),getDefClass());
        def.initFeature(this);
    }
    public abstract Actor newActor();
    public AbstractGameInstance getGame(){
        return game;
    }
    @Override
    public void setGame(AbstractGameInstance game) {
        this.game = game;
    }
    public abstract AssetGroup getAssetGroup();
    public abstract Class<? extends Def> getDefClass();


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
    public static abstract class Def implements Identifiable{
        Identifier id;
        public abstract void init(Feature feature);
        public void initFeature(Feature feature){
            feature.setId(id);
            init(feature);
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
