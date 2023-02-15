package me.carina.rpg.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import me.carina.rpg.common.file.AssetFilterProvider;
import me.carina.rpg.common.world.ClientWorld;
import me.carina.rpg.common.world.AbstractGameInstance;
import me.carina.rpg.packets.Packet;
import me.carina.rpg.packets.S2CPacket;

/**
 * Abstract implementation of a client.
 */
public abstract class Client extends AbstractGameInstance{
    public Client() {
        super(new ClientWorld(),"Client");
    }

    @Override
    public boolean shouldPack() {
        return true;
    }

    @Override
    public boolean shouldLoad(FileHandle handle, Class<?> loadClass) {
        return true;
    }
}
