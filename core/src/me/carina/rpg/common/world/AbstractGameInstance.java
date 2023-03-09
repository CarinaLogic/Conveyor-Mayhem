package me.carina.rpg.common.world;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Logger;
import me.carina.rpg.common.file.AssetFilterProvider;
import me.carina.rpg.common.file.Assets;
import me.carina.rpg.packets.Packet;

public abstract class AbstractGameInstance implements PacketHandler, AssetFilterProvider, ApplicationListener {
    Logger logger;
    Assets assets;
    public AbstractGameInstance(String loggerTag) {
        this.assets = new Assets(this,this);
        this.logger = new Logger(loggerTag);
    }

    @Override
    public void recieve(Object object) {
        if (object instanceof Packet packet) packet.onRecieve(this);
        else logger.error("Received non-packet object ("+object.getClass().getSimpleName()+"), ignoring");
    }


    public Assets getAssets() {
        return assets;
    }

    public Logger getLogger() {
        return logger;
    }

}
