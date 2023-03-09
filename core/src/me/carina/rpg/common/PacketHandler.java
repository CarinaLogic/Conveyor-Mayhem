package me.carina.rpg.common;

public interface PacketHandler {
    void send(Object object);
    void recieve(Object object);
}
