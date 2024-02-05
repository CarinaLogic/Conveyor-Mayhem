package me.carina.rpg.packets;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.packets.connection.Connection;
import me.carina.rpg.packets.connection.S2CConnection;
import me.carina.rpg.server.Server;

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
