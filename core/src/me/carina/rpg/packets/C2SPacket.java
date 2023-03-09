package me.carina.rpg.packets;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.server.Server;

public abstract class C2SPacket implements Packet{
    public C2SPacket(){}
    public abstract void onRecieve(Server server);

    @Override
    public void onRecieve(AbstractGameInstance instance) {
        if (instance instanceof Server server) onRecieve(server);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
