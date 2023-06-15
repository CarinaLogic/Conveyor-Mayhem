package me.carina.rpg.packets.connection;

import com.github.czyzby.websocket.serialization.Serializer;
import com.github.czyzby.websocket.serialization.impl.JsonSerializer;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.packets.Packet;

public abstract class Connection {
    public abstract void send(Packet object);
    public abstract boolean match(Object object);

}
