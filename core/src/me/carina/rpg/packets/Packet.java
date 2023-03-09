package me.carina.rpg.packets;

import me.carina.rpg.common.AbstractGameInstance;

public interface Packet {
    void onRecieve(AbstractGameInstance instance);
}
