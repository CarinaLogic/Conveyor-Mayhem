package me.carina.rpg;

import me.carina.rpg.packets.Packet;
import me.carina.rpg.packets.connection.S2CConnection;
import me.carina.rpg.server.Server;
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
        Game.getInstance().getLogger().debug("Sent "+object);
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
