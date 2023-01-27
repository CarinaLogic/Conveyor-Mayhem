package me.carina.rpg.server;

import me.carina.rpg.client.InternalClient;

public class InternalServer extends Server {
    InternalClient client;
    public InternalServer(InternalClient client){
        this.client = client;
    }
    @Override
    public void send(Object object) {
        client.recieve(object);
    }

}
