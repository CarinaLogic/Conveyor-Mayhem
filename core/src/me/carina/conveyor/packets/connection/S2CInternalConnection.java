package me.carina.conveyor.packets.connection;

import me.carina.conveyor.Game;
import me.carina.conveyor.client.InternalClient;
import me.carina.conveyor.packets.Packet;
import me.carina.conveyor.server.InternalServer;

public class S2CInternalConnection extends S2CConnection{
    InternalClient client;
    public S2CInternalConnection(InternalServer server, InternalClient client) {
        super(server);
        this.client = client;
    }

    @Override
    public void send(Packet object) {
        Game.getInstance().getLogger().debug(object.toString());
        client.recieve(client.getSerializer().deserialize(client.getSerializer().serializeAsString(object)), client.getConnection(server));
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
