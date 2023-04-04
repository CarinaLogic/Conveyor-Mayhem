package me.carina.rpg.common.file;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.common.*;

import java.lang.reflect.InvocationTargetException;

public class Assets {
    static Json json = new Json();
    AbstractGameInstance game;
    Array<AssetPack> assetGroups = new Array<>();
    AssetFilterProvider assetFilter;
    TextureAtlas atlas = new TextureAtlas();
    public Assets(AbstractGameInstance game, AssetFilterProvider filter){
        assetFilter = filter;
        this.game = game;
        json.setIgnoreUnknownFields(true);
    }

    public <T> T get(Path path, Class<T> type, T defaultValue){
        T value = defaultValue;
        if (ClassReflection.isAssignableFrom(Defined.class, type)){
            try {
                Defined v = (Defined) ClassReflection.newInstance(type);
                Definition def = get(path,v.getDefClass());
                def.init(v);
                value = type.cast(v);
            } catch (ReflectionException e) {
                game.getLogger().error("Could not initialize " + type.getSimpleName());
            }
        }
        else if (ClassReflection.isAssignableFrom(TextureRegion.class, type)){
            TextureRegion region = atlas.findRegion(path.toString());
            if (region != null) value = type.cast(region);
        }
        else if (ClassReflection.isAssignableFrom(Drawable.class, type)){
            TextureRegion region = get(path, TextureRegion.class);
            if (region != null) value = type.cast(new TextureRegionDrawable(region));
        }
        else {
            boolean updated = false;
            for (AssetPack group : assetGroups) {
                T t = group.get(path, type);
                if (t != null) {
                    value = t;
                    updated = true;
                }
            }
            if (!updated) {
                for (AssetPack group : assetGroups) {
                    JsonValue jsonValue = group.get(path, JsonValue.class);
                    if (jsonValue != null) {
                        value = json.readValue(type, jsonValue);
                    }
                }
            }
        }
        if (value instanceof GameObject){
            ((GameObject)value).setGame(game);
        }
        if (value instanceof Identifiable){
            ((Identifiable)value).setId(path.toIdentifier());
        }
        return value;
    }
    public <T> T get(Path path, Class<T> type){
        return get(path, type, null);
    }
    public AssetPack getGroup(Path path, Class<?> type){
        for (AssetPack group : assetGroups) {
            if (group.contains(path, type)) return group;
        }
        return null;
    }
    public boolean isLoadedBefore(Path path, Class<?> type, AssetPack group){
        for (AssetPack assetPack : assetGroups) {
            if (assetPack.equals(group)) return false;
            if (group.contains(path, type)) return true;
        }
        return false;
    }
    public void queue(FileHandle root){
        AssetPack newGroup = null;
        for (AssetPack assetPack : assetGroups) {
            if (assetPack.rootFile.equals(root)) newGroup = assetPack;
        }
        if (newGroup == null){
            newGroup = new AssetPack(game,root,this);
            assetGroups.add(newGroup);
        }
        newGroup.queueFiles();
    }
    public boolean load(){
        for (AssetPack assetPack : assetGroups) {
            if (!assetPack.finished()) {
                assetPack.progressLoad();
                return false;
            }
        }
        return true;
    }

}
