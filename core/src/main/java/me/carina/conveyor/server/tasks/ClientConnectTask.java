package me.carina.conveyor.server.tasks;

import me.carina.conveyor.Game;
import me.carina.conveyor.packets.S2CBattleMapInitPacket;
import me.carina.conveyor.packets.connection.Connection;

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
