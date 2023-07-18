package me.carina.rpg.common.file;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import me.carina.rpg.Game;

public class StringLoader extends AsynchronousAssetLoader<String, AssetLoaderParameters<String>> {
    String string;
    public StringLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<String> parameter) {
        this.string = null;
        this.string = file.readString();
    }

    @Override
    public String loadSync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<String> parameter) {
        String s = this.string;
        this.string = null;
        return s;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, AssetLoaderParameters<String> parameter) {
        return null;
    }
}
