package me.carina.conveyor;

import me.carina.conveyor.packets.Packet;
import me.carina.conveyor.packets.connection.S2CConnection;
import me.carina.conveyor.server.Server;
import org.java_websocket.WebSocket;

public class S2CExternalConnection extends S2CConnection {
    WebSocket webSocket;
    public S2CExternalConnection(Server server, WebSocket webSocket) {
        super(server);
        this.webSocket = webSocket;
    }

    @Override
    public void send(Packet object) {
        webSocket.send(getServer().getSerializer().serialize(object));
    }

    @Override
    public boolean match(Object object) {
        if (object instanceof WebSocket) {
            WebSocket socket = (WebSocket) object;
            return this.webSocket.equals(socket);
        }
        return false;
    }
}
