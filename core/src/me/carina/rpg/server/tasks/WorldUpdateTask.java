package me.carina.rpg.server.tasks;

import me.carina.rpg.common.world.ServerWorld;
import me.carina.rpg.server.Server;

public class WorldUpdateTask extends AbstractTask{
    ServerWorld world;
    public WorldUpdateTask(Server server) {
        super(server,true,false);
        this.world = (ServerWorld) server.getWorld();
    }


    @Override
    public boolean run() {
        world.update(1/60f);
        return false;
    }
}
