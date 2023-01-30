package me.carina.rpg.common.file;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;

public class AssetGroup {
    Assets assets;
    FileHandle rootFile;
    AssetManager manager;
    static ObjectMap<Files.FileType,FileHandleResolver> resolvers = new ObjectMap<>(){{
        put(Files.FileType.Internal, new InternalFileHandleResolver());
        put(Files.FileType.External, new ExternalFileHandleResolver());
        put(Files.FileType.Local, new LocalFileHandleResolver());
    }};
    static ObjectMap<Class<?>,String> extMap = new ObjectMap<>(){{
        put(Object.class, "json");
        put(Texture.class, "png");
        //put other things you need to load
    }};
    public AssetGroup(FileHandle rootFile, Assets assets){
        this.rootFile = rootFile;
        this.manager = new AssetManager(resolvers.get(rootFile.type()));
        this.assets = assets;
    }
    public void queueFiles(){
        for (FileHandle fileHandle : getFiles()) {
            if (assets.assetFilter.shouldLoad(fileHandle)){
                manager.load(fileHandle.path(), extMap.findKey(fileHandle.extension(),false));
            }
        }
    }
    public Array<FileHandle> getFiles(){
        Array<FileHandle> files = new Array<>();
        return addChildren(rootFile,files);
    }
    protected Array<FileHandle> addChildren(FileHandle fileHandle, Array<FileHandle> array){
        for (FileHandle childHandle : fileHandle.list()) {
            if (childHandle.isDirectory()) addChildren(childHandle,array);
            else array.add(childHandle);
        }
        return array;
    }

}
