package me.carina.rpg.common.command;

import me.carina.rpg.common.util.Array;

public abstract class Command {
    Array<Class<? extends Command>> nextCmdClass = new Array<>();
    public abstract void next();
    public abstract int getConsumption();
    public abstract void init(String... args);
    public abstract void run();
}
