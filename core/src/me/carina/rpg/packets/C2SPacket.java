package me.carina.rpg.packets;

import me.carina.rpg.server.Server;

public interface C2SPacket {
    void onRecieve(Server server);
}
