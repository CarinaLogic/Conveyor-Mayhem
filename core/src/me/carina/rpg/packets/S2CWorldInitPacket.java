package me.carina.rpg.packets;

import me.carina.rpg.client.Client;
import me.carina.rpg.common.world.BaseWorld;
import me.carina.rpg.common.world.WorldSerializationData;

public class S2CWorldInitPacket extends S2CPacket{
    WorldSerializationData data;
    public S2CWorldInitPacket(BaseWorld world){
        data = new WorldSerializationData(world);
    }
    @Override
    public void onRecieve(Client client) {
        client.setWorld(data.toWorld(client));
    }
}
