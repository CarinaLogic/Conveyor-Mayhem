package me.carina.conveyor.packets;

import me.carina.conveyor.packets.connection.S2CConnection;
import me.carina.conveyor.server.Server;

public class C2SUserInfoPacket extends C2SPacket{
    String userName;

    @Override
    public void onRecieve(Server server, S2CConnection connection) {

    }
}
