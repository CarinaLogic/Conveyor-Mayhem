package me.carina.rpg.packets;

import me.carina.rpg.packets.connection.S2CConnection;
import me.carina.rpg.server.Server;

public class C2STestPacket extends C2SPacket{
    @Override
    public void onRecieve(Server server, S2CConnection connection) {
        server.getLogger().debug("Test packet received");
        connection.send(new S2CMessagePacket("Hello from server!"));
    }
}
