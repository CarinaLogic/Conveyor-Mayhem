package me.carina.rpg.common;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Queue;
import com.github.czyzby.websocket.serialization.Serializer;
import com.github.czyzby.websocket.serialization.impl.JsonSerializer;
import me.carina.rpg.common.command.*;
import me.carina.rpg.common.file.AssetFilterProvider;
import me.carina.rpg.common.file.Assets;
import me.carina.rpg.packets.Packet;
import me.carina.rpg.packets.connection.Connection;

public abstract class AbstractGameInstance implements PacketHandler, AssetFilterProvider, ApplicationListener {
    Logger logger;
    Assets assets;
    Array<Connection> connections;
    Serializer serializer = new JsonSerializer();
    Queue<DirectedPacket> packetQueue = new Queue<>();
    CommandParser commandParser = new CommandParser(AddCommand.class, DivCommand.class, FocusCommand.class,
            GetCommand.class, IfEqCommand.class, JumpCommand.class, LabelCommand.class, MulCommand.class,
            PrintCommand.class, SayCommand.class, SetCommand.class, SubCommand.class, WaitCommand.class);
    public AbstractGameInstance(String loggerTag) {
        this.assets = new Assets(this,this);
        this.logger = new Logger(loggerTag);
        connections = new Array<>();
    }

    @Override
    public void recieve(Object object, Connection connection) {
        if (object instanceof Packet) {
            Packet packet = (Packet) object;
            packetQueue.addLast(new DirectedPacket(connection,packet));
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

    @Override
    public void render() {
        assets.tick();
        for (DirectedPacket packet : packetQueue) {
            packet.packet.onRecieve(this, packet.connection);
            packetQueue.removeFirst();
        }
        getCommandParser().tick();
        tick();
    }

    public abstract void tick();

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

    public Serializer getSerializer() {
        return serializer;
    }

    public CommandParser getCommandParser() {
        return commandParser;
    }

    public abstract boolean isClient();

    public static class DirectedPacket{
        Connection connection;
        Packet packet;
        public DirectedPacket(Connection connection, Packet packet){
            this.connection = connection;
            this.packet = packet;
        }
    }
}
