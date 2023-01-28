package me.carina.rpg.client;

import me.carina.rpg.packets.C2SPacket;
import me.carina.rpg.server.InternalServer;

public class InternalClient extends Client {
    InternalServer server;

    public InternalClient(){
        server = new InternalServer(this);
    }

    @Override
    public void send(Object object) {
        server.recieve(object);
    }

    public InternalServer getServer(){
        return server;
    }
}
