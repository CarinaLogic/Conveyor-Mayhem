package me.carina.rpg.packets;

import me.carina.rpg.client.Client;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.packets.connection.C2SConnection;
import me.carina.rpg.packets.connection.Connection;

public abstract class S2CPacket implements Packet{
    public S2CPacket(){}
    public abstract void onRecieve(Client client, C2SConnection connection);

    @Override
    public void onRecieve(AbstractGameInstance instance, Connection connection) {
        if (instance instanceof Client && connection instanceof C2SConnection){
            Client client = (Client) instance;
            C2SConnection con = (C2SConnection) connection;
            client.getLogger().debug("Received "+this);
            onRecieve(client,con);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
