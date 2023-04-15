package me.carina.rpg.packets.connection;

import me.carina.rpg.client.InternalClient;
import me.carina.rpg.server.InternalServer;
import me.carina.rpg.server.Server;

public class S2CInternalConnection extends S2CConnection{
    InternalClient client;
    public S2CInternalConnection(InternalServer server, InternalClient client) {
        super(server);
        this.client = client;
    }

    @Override
    public void send(Object object) {
        client.recieve(object, client.getConnection(server));
    }

    @Override
    public boolean match(Object object) {
        if (object instanceof InternalClient) {
            InternalClient client = (InternalClient) object;
            return this.client.equals(client);
        }
        return false;
    }
}
