package me.carina.rpg.packets.connection;

import com.github.czyzby.websocket.WebSocket;
import me.carina.rpg.client.ExternalClient;
import me.carina.rpg.packets.Packet;

public class C2SExternalConnection extends C2SConnection{
    WebSocket webSocket;
    public C2SExternalConnection(ExternalClient client, WebSocket socket) {
        super(client);
        webSocket = socket;
    }

    @Override
    public void send(Packet object) {
        webSocket.send(object);
    }

    @Override
    public boolean match(Object object) {
        if (object instanceof WebSocket) {
            WebSocket socket = (WebSocket) object;
            return socket.equals(webSocket);
        }
        return false;
    }
}
