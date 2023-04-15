package me.carina.rpg.common;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import me.carina.rpg.common.file.AssetFilterProvider;
import me.carina.rpg.common.file.Assets;
import me.carina.rpg.packets.Packet;
import me.carina.rpg.packets.connection.C2SConnection;
import me.carina.rpg.packets.connection.Connection;

public abstract class AbstractGameInstance implements PacketHandler, AssetFilterProvider, ApplicationListener {
    Logger logger;
    Assets assets;
    Array<Connection> connections;
    public AbstractGameInstance(String loggerTag) {
        this.assets = new Assets(this,this);
        this.logger = new Logger(loggerTag);
        connections = new Array<>();
    }

    @Override
    public void recieve(Object object, Connection connection) {
        if (object instanceof Packet) {
            Packet packet = (Packet) object;
            packet.onRecieve(this, connection);
        }
        else logger.error("Received non-packet object ("+object.getClass().getSimpleName()+"), ignoring");
    }

    public Array<Connection> getConnections() {
        return connections;
    }

    public Connection getConnection(Object object){
        for (Connection connection : connections) {
            if (connection.match(object)) return connection;
        }
        return null;
    }

    public void addConnection(Connection connection){
        connections.add(connection);
    }

    public boolean removeConnection(Connection connection){
        return connections.removeValue(connection, false);
    }

    public Assets getAssets() {
        return assets;
    }

    public Logger getLogger() {
        return logger;
    }

}
