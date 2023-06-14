package me.carina.rpg.packets.connection;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.packets.Packet;

public interface Connection {
    void send(Packet object);
    boolean match(Object object);
}
