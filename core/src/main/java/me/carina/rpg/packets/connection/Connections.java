package me.carina.rpg.packets.connection;

import me.carina.rpg.common.util.Array;
import me.carina.rpg.packets.Packet;

public class Connections {
    Array<Connection> connections = new Array<>();
    public void add(Connection connection){
        connections.add(connection);
    }
    public boolean remove(Connection connection){
        return connections.removeValue(connection,false);
    }
    public Array<Connection> getAll(){
        return connections;
    }
    public Connection get(Object o){
        for (Connection connection : connections) {
            if (connection.match(o)) return connection;
        }
        return null;
    }
    public void send(Object o, Packet packet){
        get(o).send(packet);
    }
    public void sendAll(Packet packet){
        getAll().forEach(c -> c.send(packet));
    }
}
