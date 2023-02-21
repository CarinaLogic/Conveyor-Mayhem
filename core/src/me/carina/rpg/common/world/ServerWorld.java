package me.carina.rpg.common.world;

import me.carina.rpg.client.Client;
import me.carina.rpg.common.world.BaseWorld;
import me.carina.rpg.server.Server;

public class ServerWorld extends BaseWorld {
    public ServerWorld(Server game) {
        super(game);
    }

    @Override
    public Server getGame() {
        return (Server) super.getGame();
    }

    @Override
    public void update(float step){
        world.getBodies(bodies);
        world.step(step,6,2);
    }
}
