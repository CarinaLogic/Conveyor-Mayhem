package me.carina.conveyor.common.file;

import com.badlogic.gdx.files.FileHandle;

public interface AssetFilterProvider {
    boolean shouldLoad(FileHandle handle, Class<?> loadClass);
    boolean shouldPack();
}
