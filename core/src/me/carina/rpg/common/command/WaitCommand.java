package me.carina.rpg.common.command;

import com.badlogic.gdx.Gdx;

public class WaitCommand implements Command{
    float accumulatedTime = 0;
    float targetTime = 0;
    boolean started = false;
    @Override
    public String getPrefix() {
        return "wait";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return true;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        if (!started){
            accumulatedTime = 0;
            targetTime = (float) parser.getValue(args[0]);
            started = true;
            return accumulatedTime >= targetTime;
        }
        accumulatedTime += Gdx.graphics.getDeltaTime();
        return accumulatedTime >= targetTime;
    }
}
