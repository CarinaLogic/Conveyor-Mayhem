package me.carina.rpg.server.tasks;

import me.carina.rpg.server.Server;

public abstract class TimedTask extends AbstractTask{
    float accumulatedTime;
    float step = 1/60f;
    public TimedTask(Server server) {
        super(server);
        accumulatedTime = 0;
    }

    @Override
    public void run(float delta) {
        float frameTime = Math.min(delta, 0.25f);
        accumulatedTime += frameTime;
        while (accumulatedTime >= step) {
            run();
            accumulatedTime -= step;
        }
    }

    public abstract void run();
}
