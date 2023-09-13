package me.carina.conveyor.packets.connection;

import me.carina.conveyor.packets.Packet;

public abstract class Connection {
    public abstract void send(Packet object);
    public abstract boolean match(Object object);

}
