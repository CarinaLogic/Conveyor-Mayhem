package me.carina.rpg.common.world;

public interface PacketHandler {
    void send(Object object);
    void recieve(Object object);
}
