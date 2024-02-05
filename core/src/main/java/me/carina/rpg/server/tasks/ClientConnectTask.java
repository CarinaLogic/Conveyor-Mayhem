package me.carina.rpg.server.tasks;

import me.carina.rpg.Game;
import me.carina.rpg.packets.S2CBattleMapInitPacket;
import me.carina.rpg.packets.connection.Connection;

public class ClientConnectTask extends AbstractTask{
    Connection connection;
    public ClientConnectTask(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean run() {
        connection.send(new S2CBattleMapInitPacket(Game.getServer().getTask(BattleMapUpdateTask.class).map));
        return true;
    }
}
