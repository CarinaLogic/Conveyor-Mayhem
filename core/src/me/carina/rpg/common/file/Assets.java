package me.carina.rpg.common.file;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import me.carina.rpg.common.world.AbstractGameInstance;

public class Assets {
    static Json json = new Json();
    AbstractGameInstance game;
    Array<AssetGroup> assetGroups = new Array<>();
    AssetFilterProvider assetFilter;
    TextureAtlas atlas = new TextureAtlas();
    public Assets(AbstractGameInstance game, AssetFilterProvider filter){
        assetFilter = filter;
        this.game = game;
        json.setIgnoreUnknownFields(true);
    }
    public <T> T get(String path, Class<T> type, T defaultValue){
        if (TextureRegion.class.equals(type)){
            return type.cast(atlas.findRegion(path));
        }
        for (AssetGroup group : assetGroups) {
            T t = group.get(path,type);
            if (t != null) return t;
        }
        for (AssetGroup group : assetGroups) {
            JsonValue value = group.get(path, JsonValue.class);
            if (value != null){
                return json.readValue(type,value);
            }
        }
        return defaultValue;
    }
    public <T> T get(String path, Class<T> type){
        return get(path, type, null);
    }
    public AssetGroup getGroup(String path, Class<?> type){
        for (AssetGroup group : assetGroups) {
            if (group.contains(path, type)) return group;
        }
        return null;
    }
    public boolean isLoadedBefore(String path, Class<?> type, AssetGroup group){
        for (AssetGroup assetGroup : assetGroups) {
            if (assetGroup.equals(group)) return false;
            if (group.contains(path, type)) return true;
        }
        return false;
    }
    public void queue(FileHandle root){
        AssetGroup newGroup = null;
        for (AssetGroup assetGroup : assetGroups) {
            if (assetGroup.rootFile.equals(root)) newGroup = assetGroup;
        }
        if (newGroup == null){
            newGroup = new AssetGroup(game,root,this);
            assetGroups.add(newGroup);
        }
        newGroup.queueFiles();
    }
    public boolean load(){
        for (AssetGroup assetGroup : assetGroups) {
            if (!assetGroup.finished()) {
                assetGroup.progressLoad();
                return false;
            }
        }
        return true;
    }

}
