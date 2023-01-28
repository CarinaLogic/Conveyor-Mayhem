package me.carina.rpg.packets;

import me.carina.rpg.client.Client;

public abstract class S2CPacket {
    public S2CPacket(){}
    public abstract void onRecieve(Client client);

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
