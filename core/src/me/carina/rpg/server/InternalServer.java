package me.carina.rpg.server;

import com.badlogic.gdx.utils.Array;
import me.carina.rpg.client.InternalClient;

public class InternalServer extends Server {
    Array<InternalConnection> connections = new Array<>();
    public InternalServer(InternalClient client){
        connections.add(new InternalConnection(client));
    }
    @Override
    public Array<InternalConnection> getClients() {
        return connections;
    }

    @Override
    public void send(Object object, Connection connection) {
        if (connection instanceof InternalConnection) {
            InternalConnection internalConnection = (InternalConnection) connection;
            internalConnection.client.recieve(object);
        }
    }

    @Override
    public void sendAll(Object object) {
        for (InternalConnection connection : connections) {
            connection.client.recieve(object);
        }
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
