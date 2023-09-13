package me.carina.conveyor.packets;

import me.carina.conveyor.common.AbstractGameInstance;
import me.carina.conveyor.packets.connection.Connection;

public interface Packet {
    void onRecieve(AbstractGameInstance instance, Connection connection);
}
