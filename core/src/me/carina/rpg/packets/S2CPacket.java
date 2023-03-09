package me.carina.rpg.packets;

import me.carina.rpg.client.Client;
import me.carina.rpg.common.AbstractGameInstance;

public abstract class S2CPacket implements Packet{
    public S2CPacket(){}
    public abstract void onRecieve(Client client);

    @Override
    public void onRecieve(AbstractGameInstance instance) {
        if (instance instanceof Client client) onRecieve(client);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
