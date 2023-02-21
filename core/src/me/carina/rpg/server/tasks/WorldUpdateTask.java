package me.carina.rpg.server.tasks;

import me.carina.rpg.common.world.ServerWorld;
import me.carina.rpg.server.Server;

public class WorldUpdateTask extends TimedTask{
    ServerWorld world;
    public WorldUpdateTask(Server server) {
        super(server);
        this.world = (ServerWorld) server.getWorld();
    }

    @Override
    public void run() {
        world.update(1/60f);
    }
}
