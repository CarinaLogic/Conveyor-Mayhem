package me.carina.rpg.client;

import com.badlogic.gdx.Gdx;
import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.WebSocketListener;
import com.github.czyzby.websocket.WebSockets;
import com.github.czyzby.websocket.data.WebSocketException;
import com.github.czyzby.websocket.serialization.impl.JsonSerializer;
import me.carina.rpg.packets.C2SPacket;
import me.carina.rpg.packets.S2CPacket;

public class ExternalClient extends Client {
    WebSocket socket;
    WebSocketListener listener;
    public void connect(String host, int port){
        socket = WebSockets.newSocket(WebSockets.toWebSocketUrl(host, port));
        socket.setSendGracefully(true);
        socket.setSerializer(new JsonSerializer());
        socket.setSerializeAsString(true);
        listener = new WebSocketListener() {
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
                recieve(webSocket.getSerializer().deserialize(packet));
                return true;
            }

            @Override
            public boolean onMessage(WebSocket webSocket, byte[] packet) {
                recieve(webSocket.getSerializer().deserialize(packet));
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
    }

    public void disconnect(String reason){
        if (socket == null) return;
        socket.close(reason);
    }

    @Override
    public void send(Object object) {
        if (socket == null) throw new WebSocketException();
        socket.send(object);
    }

    public boolean isConnected(){
        return socket.isOpen();
    }
}
