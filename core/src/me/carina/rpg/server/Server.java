package me.carina.rpg.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import me.carina.rpg.common.file.AssetFilterProvider;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.world.ServerWorld;
import me.carina.rpg.common.world.AbstractGameInstance;
import me.carina.rpg.packets.C2SPacket;
import me.carina.rpg.packets.Packet;

public abstract class Server extends AbstractGameInstance {
    public Server() {
        super(new ServerWorld(),"Server");
    }

    @Override
    public boolean shouldLoad(FileHandle handle, Class<?> loadClass) {
        return !Texture.class.equals(loadClass);
    }

    @Override
    public boolean shouldPack() {
        return false;
    }
}
