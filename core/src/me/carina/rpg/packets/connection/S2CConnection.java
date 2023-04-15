package me.carina.rpg.packets.connection;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.server.Server;

public abstract class S2CConnection implements Connection{
    Server server;
    public S2CConnection(Server server){
        this.server = server;
    }

    public Server getServer() {
        return server;
    }
}
