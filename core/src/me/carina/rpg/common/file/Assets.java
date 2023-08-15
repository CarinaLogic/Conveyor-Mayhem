package me.carina.rpg.common.file;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.common.*;
import me.carina.rpg.common.util.Map;

import java.lang.reflect.InvocationTargetException;

public class Assets {
    static Json json = new Json();
    AbstractGameInstance game;
    Array<AssetPack> assetGroups = new Array<>();
    AssetFilterProvider assetFilter;
    TextureAtlas atlas = new TextureAtlas();
    Map<String, Drawable> drawableCache = new Map<>();
    PixmapPacker packer = new PixmapPacker(1024,1024, Pixmap.Format.RGBA8888,2, true);
    public Assets(AbstractGameInstance game, AssetFilterProvider filter){
        assetFilter = filter;
        this.game = game;
        json.setIgnoreUnknownFields(true);
    }
    public <T extends AssetGrouped> T get(String nameSpace, String id, Class<T> type){
        return get(new Identifier(nameSpace, id),type,null);
    }
    public <T extends AssetGrouped> T get(Identifier id, Class<T> type, T defaultValue){
        try {
            //Very memory unfriendly workaround
            T t = ClassReflection.newInstance(type);
            return get(id.toPath(t.getAssetGroup()), type, defaultValue);
        } catch (ReflectionException e) {
            game.getLogger().error("Could not initialize " + type.getSimpleName());
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public <T> T get(Path path, Class<T> type, T defaultValue){
        T value = defaultValue;
        if (ClassReflection.isAssignableFrom(Defined.class, type)){
            try {
                Defined v = (Defined) ClassReflection.newInstance(type);
                value = (T) v;
            } catch (ReflectionException e) {
                game.getLogger().error("Could not initialize " + type.getSimpleName());
            }
        }
        else if (ClassReflection.isAssignableFrom(Drawable.class, type)){
            if (drawableCache.containsKey(path.toString())) value = (T) drawableCache.get(path.toString());
            else{
                if (ClassReflection.isAssignableFrom(NinePatchDrawable.class,type)){
                    NinePatch patch = atlas.createPatch(path.toString());
                    if (patch != null){
                        value = (T) new NinePatchDrawable(patch);
                        drawableCache.put(path.toString(), (Drawable) value);
                    }
                }
                else {
                    TextureRegion region = atlas.findRegion(path.toString());
                    if (region != null) {
                        value = (T) new TextureRegionDrawable(region);
                        drawableCache.put(path.toString(), (Drawable) value);
                    }
                }
            }
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
        if (value instanceof Identifiable){
            ((Identifiable)value).setId(path.toIdentifier());
        }
        if (value instanceof Defined){
            Definition def = get(path,((Defined)value).getDefClass());
            if (def != null) def.init(((Defined) value));
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
    public void tick(){
        packer.updateTextureAtlas(atlas, Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest, false);
    }
    public void registerTexture(Path path, Pixmap pixmap){
        packer.pack(path.toString(),pixmap);
    }

}
