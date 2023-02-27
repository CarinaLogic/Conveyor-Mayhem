package me.carina.rpg.server;

import me.carina.rpg.client.InternalClient;
import me.carina.rpg.packets.S2CPacket;

public class InternalServer extends Server {
    InternalClient client;
    public InternalServer(InternalClient client){
        this.client = client;
    }
    @Override
    public void send(Object object) {
        client.recieve(object);
    }

    @Override
    public void open(int port) {

    }

    @Override
    public void close(String reason) {

    }

    @Override
    public boolean isOpen() {
        return true;
    }
}
