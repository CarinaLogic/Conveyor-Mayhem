package me.carina.conveyor.packets;

import me.carina.conveyor.common.AbstractGameInstance;
import me.carina.conveyor.packets.connection.Connection;
import me.carina.conveyor.packets.connection.S2CConnection;
import me.carina.conveyor.server.Server;

public abstract class C2SPacket implements Packet{
    public C2SPacket(){}
    public abstract void onRecieve(Server server, S2CConnection connection);

    @Override
    public void onRecieve(AbstractGameInstance instance, Connection connection) {
        if (instance instanceof Server && connection instanceof S2CConnection){
            Server server = (Server) instance;
            S2CConnection con = (S2CConnection) connection;
            onRecieve(server,con);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
