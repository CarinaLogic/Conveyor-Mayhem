package me.carina.rpg.common;

import me.carina.rpg.packets.connection.Connection;

public interface PacketHandler {
    void recieve(Object object, Connection connection);
}
