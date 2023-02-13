package me.carina.rpg.common.world;

import me.carina.rpg.common.file.AssetFilterProvider;
import me.carina.rpg.common.file.Assets;
import me.carina.rpg.packets.Packet;

public abstract class AbstractGameInstance implements PacketHandler, AssetFilterProvider {
    BaseWorld world;
    Assets assets;
    public AbstractGameInstance(BaseWorld world){
        this.world = world;
        this.assets = new Assets(this);
    }

    @Override
    public void recieve(Object object) {
        if (object instanceof Packet packet) packet.onRecieve(this);
        //insert logger here
    }

    public BaseWorld getWorld(){
        return world;
    }
}
