package me.carina.rpg.common.file;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.github.czyzby.websocket.serialization.impl.JsonSerializer;
import me.carina.rpg.common.world.AbstractGameInstance;

public class JsonAssetLoader extends AsynchronousAssetLoader<JsonValue, AssetLoaderParameters<JsonValue>> {
    JsonValue asset;
    AbstractGameInstance game;
    AssetGroup group;
    public JsonAssetLoader(AssetGroup group, AbstractGameInstance game){
        super(group.getResolver());
        this.game = game;
        this.group = group;
    }



    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<JsonValue> parameter) {
        this.asset = null;
        JsonValue value = new JsonReader().parse(file.readString());
        String parentName = value.getString("parent",null);
        if (parentName != null){
            JsonValue parent = game.getAssets().get(parentName,null);
            for (JsonValue entry : value){
                parent.addChild(entry);
                this.asset = parent;
            }
        }
        else {this.asset = value;}
    }

    @Override
    public JsonValue loadSync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<JsonValue> parameter) {
        JsonValue t = this.asset;
        this.asset = null;
        return t;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, AssetLoaderParameters<JsonValue> parameter) {
        JsonValue value = new JsonReader().parse(file.readString());
        String parentName = value.getString("parent",null);
        if (parentName != null){
            JsonValue t = Assets.json.readValue(null, value);
            if (!game.getAssets().isLoadedBefore(parentName,t.getClass(),group)){
                return new Array<>(){{
                    add(new AssetDescriptor<>(group.getHandle(parentName,t.getClass()),t.getClass()));
                }};
            }
        }
        return null;
    }
}
