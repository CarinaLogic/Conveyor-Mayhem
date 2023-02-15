package me.carina.rpg.common.file;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class Assets {
    Array<AssetGroup> assetGroups = new Array<>();
    AssetFilterProvider assetFilter;
    TextureAtlas atlas = new TextureAtlas();
    public Assets(AssetFilterProvider filter){
        assetFilter = filter;
    }
    public <T> T get(String path, Class<T> type, T defaultValue){
        for (AssetGroup group : assetGroups) {
            T t = group.get(path,type);
            if (t != null) return t;
        }
        return defaultValue;
    }
    public <T> T get(String path, Class<T> type){
        return get(path, type, null);
    }
    public void queue(FileHandle root){
        AssetGroup newGroup = null;
        for (AssetGroup assetGroup : assetGroups) {
            if (assetGroup.rootFile.equals(root)) newGroup = assetGroup;
        }
        if (newGroup == null){
            newGroup = new AssetGroup(root,this);
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
