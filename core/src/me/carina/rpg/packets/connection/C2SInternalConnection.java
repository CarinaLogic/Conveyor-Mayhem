package me.carina.rpg.packets.connection;

import me.carina.rpg.client.Client;
import me.carina.rpg.client.InternalClient;
import me.carina.rpg.server.InternalServer;
import me.carina.rpg.server.Server;

public class C2SInternalConnection extends C2SConnection{
    InternalServer server;
    public C2SInternalConnection(InternalClient client, InternalServer server) {
        super(client);
        this.server = server;
    }

    @Override
    public void send(Object object) {
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
