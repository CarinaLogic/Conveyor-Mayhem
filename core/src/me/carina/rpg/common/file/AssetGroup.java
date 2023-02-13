package me.carina.rpg.common.file;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.PixmapPackerIO;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;

import java.util.Objects;
import java.util.Stack;

public class AssetGroup {
    Assets assets;
    FileHandle rootFile;
    AssetManager manager;
    static ObjectMap<Files.FileType,FileHandleResolver> resolvers = new ObjectMap<>(){{
        put(Files.FileType.Internal, new InternalFileHandleResolver());
        put(Files.FileType.External, new ExternalFileHandleResolver());
        put(Files.FileType.Local, new LocalFileHandleResolver());
    }};
    static ObjectMap<Class<?>,Array<String>> extMap = new ObjectMap<>(){{
        put(Object.class, new Array<>(){{add("json");}});
        put(Texture.class, new Array<>(){{add("png","jpg","bmp");}});
        //put other things you need to load
    }};
    public static Class<?> getLoadClass(String ext){
        for (ObjectMap.Entry<Class<?>, Array<String>> entry : extMap) {
            if (entry.value.contains(ext,false)) return entry.key;
        }
        return null;
    }
    public static Array<String> addExt(FileHandle handle, Class<?> loadClass){
        Array<String> paths = new Array<>();
        extMap.get(loadClass).forEach(s -> paths.add(handle.pathWithoutExtension()+"."+s));
        return paths;
    }
    public AssetGroup(FileHandle rootFile, Assets assets){
        this.rootFile = rootFile;
        this.manager = new AssetManager(resolvers.get(rootFile.type()));
        this.assets = assets;
    }
    public void queueFiles(){
        getFiles().forEach(f -> manager.load(f.path(), getLoadClass(f.extension())));
    }
    public void progressLoad(){
        if (!manager.isFinished()) {
            manager.update(16);
        }
        else {
            if (assets.assetFilter.shouldPack()) {
                PixmapPacker pixmapPacker = new PixmapPacker(1024,1024, Pixmap.Format.RGBA8888,2, true);
                getFiles().forEach(f -> {
                    if (Texture.class.equals(getLoadClass(f.extension()))){
                        Texture t = manager.get(f.path());
                        Pixmap p = t.getTextureData().consumePixmap();
                        pixmapPacker.pack(f.pathWithoutExtension(),p);
                    }
                });
                pixmapPacker.updateTextureAtlas(assets.atlas, Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest, false);
            }
        }

    }
    public boolean finished(){return manager.isFinished();}
    public <T> T get(String path, Class<T> cls){
        for (String s : addExt(getHandle(path), cls)) {
            if (manager.contains(s)) return manager.get(s);
        }
        return null;
    }
    //already filtered by assetFilter
    public Array<FileHandle> getFiles(){
        Array<FileHandle> files = new Array<>();
        return addChildren(rootFile,files);
    }
    public FileHandle getHandle(String path){
        FileHandle handle = rootFile;
        for (String s : path.split("/")) {
            handle = handle.child(s);
        }
        return handle;
    }
    protected Array<FileHandle> addChildren(FileHandle fileHandle, Array<FileHandle> array){
        for (FileHandle childHandle : fileHandle.list()) {
            if (childHandle.isDirectory()) addChildren(childHandle,array);
            else if (assets.assetFilter.shouldLoad(childHandle)) array.add(childHandle);
        }
        return array;
    }
}
