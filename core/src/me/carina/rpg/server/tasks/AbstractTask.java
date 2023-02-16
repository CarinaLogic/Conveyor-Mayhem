package me.carina.rpg.server.tasks;

import me.carina.rpg.server.Server;

public abstract class AbstractTask {
    Server server;
    public AbstractTask(Server server){
        this.server = server;
    }
    public abstract void run(float delta);
}
