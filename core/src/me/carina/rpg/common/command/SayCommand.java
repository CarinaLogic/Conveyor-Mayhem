package me.carina.rpg.common.command;

import me.carina.rpg.Game;

import java.util.Arrays;
import java.util.StringJoiner;

public class SayCommand extends Command{
    String msg;
    @Override
    public void next() {

    }

    @Override
    public int getConsumption() {
        return 1;
    }

    @Override
    public void init(String... args) {
        for (int i = 1; i < args.length; i++) {
            msg += (args[i] + " ");
        }
        msg = msg.trim();
    }

    @Override
    public void run() {
        Game.getInstance().getLogger().info(msg);
    }
}
