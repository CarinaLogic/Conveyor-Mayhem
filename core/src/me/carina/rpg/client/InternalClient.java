package me.carina.rpg.client;

import me.carina.rpg.server.InternalServer;

public class InternalClient extends AbstractClient{
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
