package me.carina.rpg.packets.connection;

import me.carina.rpg.client.InternalClient;
import me.carina.rpg.packets.Packet;
import me.carina.rpg.server.InternalServer;

public class C2SInternalConnection extends C2SConnection{
    InternalServer server;
    public C2SInternalConnection(InternalClient client, InternalServer server) {
        super(client);
        this.server = server;
    }

    @Override
    public void send(Packet object) {
        server.recieve(object, server.getConnection(client));
    }

    @Override
    public boolean match(Object object) {
        if (object instanceof InternalServer) {
            InternalServer server = (InternalServer) object;
            return this.server.equals(server);
        }
        return false;
    }

}
