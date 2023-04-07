package me.carina.rpg.packets;

import com.badlogic.gdx.utils.Array;
import me.carina.rpg.server.Connection;

public abstract class S2CTargetPacket extends S2CPacket{
    transient Array<Connection> targets = new Array<>();
    public S2CTargetPacket addTarget(Connection... connections){
        targets.addAll(connections);
        return this;
    }

    public Array<Connection> getTargets() {
        return targets;
    }
}
