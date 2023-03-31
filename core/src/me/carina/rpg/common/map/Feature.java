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
public abstract class Feature implements GameObject{
    transient AbstractGameInstance game;
    public Feature(AbstractGameInstance game, Def def){
        this.game = game;
        def.init(this);
    }
    public Feature(AbstractGameInstance game, String id){
        this.game = game;
        String path = getTypePrefix() + "/" + id;
        Def def = game.getAssets().get(path,getDefClass());
        def.init(this);
    }
    public abstract Actor newActor();
    public AbstractGameInstance getGame(){
        return game;
    }
    @Override
    public void setGame(AbstractGameInstance game) {
        this.game = game;
    }
    public abstract String getTypePrefix();
    public abstract Class<? extends Def> getDefClass();
    public static abstract class Def{
        public abstract void init(Feature feature);
    }
}
