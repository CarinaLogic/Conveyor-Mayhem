package me.carina.rpg.server.tasks;

import me.carina.rpg.server.Server;

public class BattleMapUpdateTask extends AbstractTask{
    public BattleMapUpdateTask(Server server) {
        super(server, true, false);
    }

    @Override
    public boolean run() {
        return false;
    }
}
