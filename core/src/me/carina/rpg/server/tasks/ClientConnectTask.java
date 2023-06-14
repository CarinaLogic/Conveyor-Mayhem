package me.carina.rpg.server.tasks;

import com.badlogic.gdx.utils.Queue;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.packets.S2CBattleMapInitPacket;
import me.carina.rpg.packets.connection.Connection;
import me.carina.rpg.server.Server;

public class ClientConnectTask extends AbstractTask{
    Connection connection;
    public ClientConnectTask(Server server, Connection connection) {
        super(server);
        this.connection = connection;
    }

    @Override
    public boolean run() {
        connection.send(new S2CBattleMapInitPacket(new BattleMap(server)));
        return true;
    }
}
