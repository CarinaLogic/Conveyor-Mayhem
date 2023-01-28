package me.carina.rpg.client;

import me.carina.rpg.packets.S2CPacket;
import me.carina.rpg.server.AbstractExternalServer;

public class NullExternalServer extends AbstractExternalServer {
    @Override
    public void open(int port) {

    }

    @Override
    public void close(String reason) {

    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void send(Object object) {

    }
}
