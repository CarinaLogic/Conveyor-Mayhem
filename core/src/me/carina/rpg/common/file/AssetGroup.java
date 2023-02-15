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
import me.carina.rpg.common.util.ArrayMap;
import me.carina.rpg.common.util.TripleMap;

import java.util.Objects;
import java.util.Stack;

public class AssetGroup {
    Assets assets;
    FileHandle rootFile;
    AssetManager manager;
    static final ObjectMap<Files.FileType,FileHandleResolver> resolvers = new ObjectMap<>(){{
        put(Files.FileType.Internal, new InternalFileHandleResolver());
        put(Files.FileType.External, new ExternalFileHandleResolver());
        put(Files.FileType.Local, new LocalFileHandleResolver());
    }};
    static final ArrayMap<Class<?>,String> extMap = new ArrayMap<>(){{
        put(Object.class, "json");
        put(Texture.class, "png", "bmp", "jpg");
        //put other things you need to load
    }};
    TripleMap<String,Class<?>,FileHandle> pathMap = new TripleMap<>();
    public AssetGroup(FileHandle rootFile, Assets assets){
        this.rootFile = rootFile;
        this.manager = new AssetManager(resolvers.get(rootFile.type()));
        this.assets = assets;
    }
    public void queueFiles(){
        getFiles().forEach(f -> {
            Class<?> cls = extMap.findKey(f.extension(),false);
            manager.load(f.path(), cls);
            pathMap.put(getShortenedPath(f),cls,f);
        });
    }
    public void progressLoad(){
        if (!manager.isFinished()) {
            manager.update(16);
        }
        else {
            if (assets.assetFilter.shouldPack()) {
                PixmapPacker pixmapPacker = new PixmapPacker(1024,1024, Pixmap.Format.RGBA8888,2, true);
                getFiles().forEach(f -> {
                    if (Texture.class.equals(extMap.findKey(f.extension(),false))){
                        Texture t = manager.get(f.path());
                        Pixmap p = t.getTextureData().consumePixmap();
                        pixmapPacker.pack(f.path(),p);
                    }
                });
                pixmapPacker.updateTextureAtlas(assets.atlas, Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest, false);
            }
        }

    }
    public boolean finished(){return manager.isFinished();}
    public <T> T get(String path, Class<T> type){
        return get(path,type,null);
    }
    public <T> T get(String path, Class<T> type, T defaultValue){
        FileHandle f = pathMap.get(path,type);
        if (f != null) return manager.get(f.path(),type);
        return defaultValue;
    }
    public String getShortenedPath(FileHandle handle){
        String[] paths = handle.pathWithoutExtension().replace(rootFile.path()+"/","").split("/");
        return paths[0] + "/" + paths[paths.length-1];
    }
    //already filtered by assetFilter
    //absolute path, root already added
    public Array<FileHandle> getFiles(){
        Array<FileHandle> files = new Array<>();
        return addChildren(rootFile,files);
    }
    protected Array<FileHandle> addChildren(FileHandle fileHandle, Array<FileHandle> array){
        for (FileHandle childHandle : fileHandle.list()) {
            if (childHandle.isDirectory()) addChildren(childHandle,array);
            else if (assets.assetFilter.shouldLoad(childHandle,extMap.findKey(childHandle.extension(), false))) array.add(childHandle);
        }
        return array;
    }
}
