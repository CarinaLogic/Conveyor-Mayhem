package me.carina.rpg;

import com.github.czyzby.websocket.serialization.Serializer;
import com.github.czyzby.websocket.serialization.impl.JsonSerializer;
import me.carina.rpg.server.AbstractExternalServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

/**
 * Represents a server which is hosted externally.
 */
public class ExternalServer extends AbstractExternalServer {
    WebSocketServer server;
    Serializer serializer = new JsonSerializer();
    boolean isOpen = false;
    public void open(int port){
        server = new WebSocketServer(new InetSocketAddress(port)) {
            @Override
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                getLogger().info("Client handshake received "+conn.getProtocol());
                ExternalServer.this.addConnection(new S2CExternalConnection(ExternalServer.this, conn));
            }

            @Override
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {
                if (ExternalServer.this.removeConnection(getConnection(conn))) getLogger().error("Client disconnected " + conn.getProtocol().toString());
                else getLogger().error("Received disconnect message from unknown connection "+conn.getProtocol().toString());
            }

            @Override
            public void onMessage(WebSocket conn, String message) {
                recieve(serializer.deserialize(message), getConnection(conn));
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


    public Serializer getSerializer() {
        return serializer;
    }

}
