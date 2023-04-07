package me.carina.rpg.client;

import com.badlogic.gdx.utils.Array;
import me.carina.rpg.packets.S2CPacket;
import me.carina.rpg.server.AbstractExternalServer;
import me.carina.rpg.server.Connection;
import me.carina.rpg.server.InternalConnection;

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

    @Override
    public Array<InternalConnection> getClients() {
        return null;
    }

    @Override
    public void send(Object object, Connection connection) {

    }

    @Override
    public void sendAll(Object object) {

    }
}
