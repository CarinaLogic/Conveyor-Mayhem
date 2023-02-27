package me.carina.rpg.common.file;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import me.carina.rpg.common.util.ArrayMap;
import me.carina.rpg.common.util.TripleMap;
import me.carina.rpg.common.world.AbstractGameInstance;

public class AssetGroup {
    boolean finished = false;
    AbstractGameInstance game;
    Assets assets;
    FileHandle rootFile;
    AssetManager manager;
    static final ObjectMap<Files.FileType,FileHandleResolver> resolvers = new ObjectMap<>(){{
        put(Files.FileType.Internal, new InternalFileHandleResolver());
        put(Files.FileType.External, new ExternalFileHandleResolver());
        put(Files.FileType.Local, new LocalFileHandleResolver());
    }};
    static final ArrayMap<Class<?>,String> extMap = new ArrayMap<>(){{
        put(JsonValue.class, "json");
        put(Texture.class, "png", "bmp", "jpg");
        //put other things you need to load
    }};
    TripleMap<String,Class<?>,FileHandle> pathMap = new TripleMap<>();
    public AssetGroup(AbstractGameInstance game,FileHandle rootFile, Assets assets){
        this.rootFile = rootFile;
        this.manager = new AssetManager(resolvers.get(rootFile.type()));
        this.assets = assets;
        this.game = game;
        this.manager.setLoader(JsonValue.class,new JsonAssetLoader(this,game));
    }
    public void queueFiles(){
        getFiles().forEach(f -> {
            Class<?> cls = extMap.findKey(f.extension(),false);
            if (cls != null) pathMap.put(getShortenedPath(f),cls,f);
        });
        getFiles().forEach(f -> {
            Class<?> cls = extMap.findKey(f.extension(),false);
            if (cls != null) manager.load(f.path(), cls);
        });
        finished = false;
    }
    public void progressLoad(){
        finished = false;
        if (!manager.isFinished()) {
            manager.update(16);
        }
        else {
            if (assets.assetFilter.shouldPack()) {
                PixmapPacker pixmapPacker = new PixmapPacker(1024,1024, Pixmap.Format.RGBA8888,2, true);
                getFiles().forEach(f -> {
                    if (Texture.class.equals(extMap.findKey(f.extension(),false))){
                        Texture t = manager.get(f.path());
                        TextureData data = t.getTextureData();
                        if (!data.isPrepared()) data.prepare();
                        Pixmap p = data.consumePixmap();
                        pixmapPacker.pack(getShortenedPath(f),p);
                    }
                });
                pixmapPacker.updateTextureAtlas(assets.atlas, Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest, false);
            }
            finished = true;
        }

    }
    public boolean contains(String path, Class<?> type){
        return manager.contains(pathMap.get(path,type).path(),type);
    }
    public boolean finished(){
        return finished;
    }
    public <T> T get(String path, Class<T> type){
        return get(path,type,null);
    }
    public <T> T get(String path, Class<T> type, T defaultValue){
        FileHandle f = pathMap.get(path,type);
        if (f != null && type == null) return manager.get(f.path());
        else if (f != null) return manager.get(f.path(),type);
        return defaultValue;
    }
    public String getShortenedPath(FileHandle handle){
        String[] paths = handle.pathWithoutExtension().replace(rootFile.path()+"/","").split("/");
        return paths[0] + "/" + paths[paths.length-1];
    }
    public FileHandleResolver getResolver(){
        return manager.getFileHandleResolver();
    }
    public FileHandle getHandle(String path, Class<?> type){
        return pathMap.get(path, type);
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
