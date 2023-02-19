package me.carina.rpg.common.world;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Logger;
import me.carina.rpg.common.file.AssetFilterProvider;
import me.carina.rpg.common.file.Assets;
import me.carina.rpg.packets.Packet;

public abstract class AbstractGameInstance implements PacketHandler, AssetFilterProvider, ApplicationListener {
    BaseWorld world;
    Logger logger;
    Assets assets;
    Json json = new Json();
    public AbstractGameInstance(BaseWorld world, String loggerTag) {
        this.world = world;
        this.assets = new Assets(this,this);
        this.logger = new Logger(loggerTag);
        world.setGame(this);
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

    public BaseWorld getWorld(){
        return world;
    }
}
