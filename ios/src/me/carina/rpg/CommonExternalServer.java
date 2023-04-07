package me.carina.rpg;

import com.badlogic.gdx.utils.Array;
import com.github.czyzby.websocket.serialization.Serializer;
import com.github.czyzby.websocket.serialization.impl.JsonSerializer;
import me.carina.rpg.server.AbstractExternalServer;
import me.carina.rpg.server.Connection;
import me.carina.rpg.server.InternalConnection;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Collections;

/**
 * Represents a server which is hosted externally.
 */
public class CommonExternalServer extends AbstractExternalServer {
    WebSocketServer server;
    Serializer serializer = new JsonSerializer();
    Array<CommonExternalConnection> connections = new Array<>();
    boolean isOpen = false;
    public void open(int port){
        server = new WebSocketServer(new InetSocketAddress(port)) {
            @Override
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                getLogger().info("Client handshake received "+conn.getProtocol());
                connections.add(new CommonExternalConnection(conn));
            }

            @Override
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {
                for (CommonExternalConnection connection : connections) {
                    if (connection.webSocket.equals(conn)){
                        connections.removeValue(connection,false);
                        getLogger().info("Client disconnected "+connection.toString());
                        return;
                    }
                }
                getLogger().error("Received disconnect message from unknown connection "+conn.getProtocol().toString());
            }

            @Override
            public void onMessage(WebSocket conn, String message) {
                recieve(serializer.deserialize(message));
            }

            @Override
            public void onError(WebSocket conn, Exception ex) {
                try {
                    throw ex;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onStart() {

            }
        };
        server.start();
        isOpen = true;
    }

    @Override
    public void close(String reason) {
        try {
            server.stop(1000,reason);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        isOpen = false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public Array<CommonExternalConnection> getClients() {
        return connections;
    }

    @Override
    public void send(Object object, Connection connection) {
        if (connection instanceof CommonExternalConnection) {
            CommonExternalConnection conn = (CommonExternalConnection) connection;
            server.broadcast(serializer.serializeAsString(object), Collections.singleton(conn.webSocket));
        }
    }

    @Override
    public void sendAll(Object object) {
        server.broadcast(serializer.serializeAsString(object));
    }
}
