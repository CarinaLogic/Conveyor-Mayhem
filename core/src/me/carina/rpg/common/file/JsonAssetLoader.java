package me.carina.rpg.common.file;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.carina.rpg.common.AbstractGameInstance;

public class JsonAssetLoader extends AsynchronousAssetLoader<JsonValue, AssetLoaderParameters<JsonValue>> {
    JsonValue asset;
    AbstractGameInstance game;
    AssetPack group;
    public JsonAssetLoader(AssetPack group, AbstractGameInstance game){
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
            JsonValue parent = game.getAssets().get(parentName, JsonValue.class);
            for (JsonValue entry : value){
                if (!entry.name().equals("parent")) parent.addChild(entry);
            }
            this.asset = parent;
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
        Array<AssetDescriptor> array = new Array<>();
        JsonValue value = new JsonReader().parse(file.readString());
        String parentName = value.getString("parent",null);
        if (parentName != null){
            if (!game.getAssets().isLoadedBefore(parentName, JsonValue.class,group)){
                array.add(new AssetDescriptor<>(group.getHandle(parentName, JsonValue.class), JsonValue.class));
            }
        }
        return array;
    }
}
