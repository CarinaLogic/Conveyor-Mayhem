package me.carina.rpg.client;

import com.badlogic.gdx.utils.Array;
import me.carina.rpg.server.AbstractExternalServer;
import me.carina.rpg.server.Connection;

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
    public Array<Connection> getClients() {
        return null;
    }

    @Override
    public void send(Object object, Connection connection) {

    }

    @Override
    public void sendAll(Object object) {

    }
}
