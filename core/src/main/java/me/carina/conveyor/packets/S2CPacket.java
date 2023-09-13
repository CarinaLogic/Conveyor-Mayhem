package me.carina.conveyor.packets;

import me.carina.conveyor.client.Client;
import me.carina.conveyor.common.AbstractGameInstance;
import me.carina.conveyor.packets.connection.C2SConnection;
import me.carina.conveyor.packets.connection.Connection;

public abstract class S2CPacket implements Packet{
    public S2CPacket(){}
    public abstract void onRecieve(Client client, C2SConnection connection);

    @Override
    public void onRecieve(AbstractGameInstance instance, Connection connection) {
        if (instance instanceof Client && connection instanceof C2SConnection){
            Client client = (Client) instance;
            C2SConnection con = (C2SConnection) connection;
            onRecieve(client,con);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
