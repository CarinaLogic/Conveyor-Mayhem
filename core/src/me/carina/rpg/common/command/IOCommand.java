package me.carina.rpg.common.command;

import me.carina.rpg.Game;

public class IOCommand extends Command{
    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction
    public void print(Object o){
        Game.getInstance().getLogger().info(o.toString());
    }
    @CommandFunction
    public void wait_$(Double seconds){
        getScript().waitTime += seconds;
    }
}
