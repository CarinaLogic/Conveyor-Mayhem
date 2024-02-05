package me.carina.rpg.client;

import com.badlogic.gdx.Gdx;
import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.WebSocketListener;
import com.github.czyzby.websocket.WebSockets;
import com.github.czyzby.websocket.serialization.impl.JsonSerializer;
import me.carina.rpg.packets.connection.C2SExternalConnection;
import me.carina.rpg.packets.connection.Connection;

public class ExternalClient extends Client {
    public Connection connect(String host, int port){
        WebSocket socket = WebSockets.newSocket(WebSockets.toWebSocketUrl(host, port));
        socket.setSendGracefully(true);
        socket.setSerializer(new JsonSerializer());
        socket.setSerializeAsString(true);
        WebSocketListener listener = new WebSocketListener() {
            @Override
            public boolean onOpen(WebSocket webSocket) {
                Gdx.app.debug("Client","Connected");
                return false;
            }

            @Override
            public boolean onClose(WebSocket webSocket, int closeCode, String reason) {
                return false;
            }

            @Override
            public boolean onMessage(WebSocket webSocket, String packet) {
                recieve(webSocket.getSerializer().deserialize(packet), getConnection(webSocket));
                return true;
            }

            @Override
            public boolean onMessage(WebSocket webSocket, byte[] packet) {
                recieve(webSocket.getSerializer().deserialize(packet), getConnection(webSocket));
                return true;
            }

            @Override
            public boolean onError(WebSocket webSocket, Throwable error) {
                try {
                    throw error;
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        };
        socket.addListener(listener);
        socket.connect();
        return new C2SExternalConnection(this, socket);
    }
}
