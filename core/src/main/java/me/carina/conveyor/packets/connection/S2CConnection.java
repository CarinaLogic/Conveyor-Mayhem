package me.carina.conveyor.packets.connection;

import me.carina.conveyor.server.Server;

public abstract class S2CConnection extends Connection{
    Server server;
    public S2CConnection(Server server){
        this.server = server;
    }

    public Server getServer() {
        return server;
    }
}
