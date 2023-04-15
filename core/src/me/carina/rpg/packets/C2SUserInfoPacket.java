package me.carina.rpg.packets;

import me.carina.rpg.packets.connection.S2CConnection;
import me.carina.rpg.server.Server;

public class C2SUserInfoPacket extends C2SPacket{
    String userName;

    @Override
    public void onRecieve(Server server, S2CConnection connection) {

    }
}
