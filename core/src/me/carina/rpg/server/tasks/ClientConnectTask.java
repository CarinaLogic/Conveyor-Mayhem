package me.carina.rpg.server.tasks;

import me.carina.rpg.server.Server;

public class ClientConnectTask extends AbstractTask{
    public ClientConnectTask(Server server) {
        super(server);
    }

    @Override
    public boolean run() {
        return false;
    }
}
