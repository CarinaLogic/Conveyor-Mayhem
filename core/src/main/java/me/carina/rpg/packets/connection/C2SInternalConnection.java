package me.carina.rpg.packets.connection;

import me.carina.rpg.Game;
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
        //Serialize and deserialize to remove transient fields, making it act as the same as external connections
        server.recieve(server.getSerializer().deserialize(server.getSerializer().serialize(object)), server.getConnections().get(client));
        Game.getInstance().getLogger().debug("Sent "+object);
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
