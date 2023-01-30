package me.carina.rpg.common.file;

import com.badlogic.gdx.files.FileHandle;

public interface AssetFilter {
    boolean shouldLoad(FileHandle handle);
}
