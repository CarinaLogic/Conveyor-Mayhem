package me.carina.rpg;

import com.badlogic.gdx.Gdx;
import com.github.czyzby.websocket.data.WebSocketException;
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
public class CommonExternalServer extends AbstractExternalServer {
    public WebSocketServer server;
    Serializer serializer = new JsonSerializer();
    public void open(int port){
        server = new WebSocketServer(new InetSocketAddress(port)) {
            @Override
            public void onOpen(WebSocket conn, ClientHandshake handshake) {

                Gdx.app.debug("Server", "Client connected "+conn.getProtocol().toString());
            }

            @Override
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {

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
    }

    @Override
    public void close(String reason) {
        try {
            server.stop(1000,reason);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void send(Object object) {
        if (server == null) throw new WebSocketException();
        server.broadcast(serializer.serializeAsString(object));
    }
}
