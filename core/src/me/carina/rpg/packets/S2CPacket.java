package me.carina.rpg.packets;

import me.carina.rpg.client.Client;

public interface S2CPacket {
    void onRecieve(Client client);
}
