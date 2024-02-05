package me.carina.rpg.packets.connection;

import me.carina.rpg.packets.Packet;

public abstract class Connection {
    public abstract void send(Packet object);
    public abstract boolean match(Object object);

}
