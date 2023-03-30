package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.GameObject;

//Basic design around actual feature objects
//1. The parent object, which holds all the info processed by sever
//   should be able to convert to json, aside from game (which is initialized upon loading)
//2. The Actor object, which holds rendering info, and renders itself
//3. The definition object, which is provided by assets in the form of json, which is used to construct parent
//   optional, should be simple
public abstract class AbstractFeature<T extends AbstractFeature.Def> implements GameObject {
    transient AbstractGameInstance game;
    public AbstractFeature(AbstractGameInstance game, String id){
        this.game = game;
        String path = getTypePrefix() + "/" + id;
        T def = game.getAssets().get(path,getDefClass());
        init(def);
    }
    public AbstractFeature(AbstractGameInstance game, T def){
        this.game = game;
        init(def);
    }
    public abstract void init(T def);
    public abstract Actor newActor();
    public AbstractGameInstance getGame(){
        return game;
    }
    @Override
    public void setGame(AbstractGameInstance game) {
        this.game = game;
    }
    public abstract Class<T> getDefClass();
    public abstract String getTypePrefix();
    public static abstract class Def{}
}
