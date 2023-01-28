package me.carina.rpg.packets;

import me.carina.rpg.server.Server;

public abstract class C2SPacket {
    public C2SPacket(){}
    public abstract void onRecieve(Server server);
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
