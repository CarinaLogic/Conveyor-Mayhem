package me.carina.rpg.packets;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.packets.connection.Connection;

public interface Packet {
    void onRecieve(AbstractGameInstance instance, Connection connection);
}
