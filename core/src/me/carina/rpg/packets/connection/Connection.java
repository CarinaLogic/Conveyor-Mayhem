package me.carina.rpg.packets.connection;

import me.carina.rpg.common.AbstractGameInstance;

public interface Connection {
    void send(Object object);
    boolean match(Object object);
}
