package me.carina.conveyor.common;

import me.carina.conveyor.packets.connection.Connection;

public interface PacketHandler {
    void recieve(Object object, Connection connection);
}
