package me.carina.rpg.common.command;

import me.carina.rpg.common.util.Array;

public class CommandParser {
    BaseNode baseNode;
    public void execute(String cmd){
        String[] args = cmd.split(" ");
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
        }
        CommandNode<?> node;
        while (true){

        }
    }
}
