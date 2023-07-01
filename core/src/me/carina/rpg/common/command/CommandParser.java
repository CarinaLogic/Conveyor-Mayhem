package me.carina.rpg.common.command;

import com.badlogic.gdx.utils.reflect.ArrayReflection;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.Game;

import java.util.Arrays;

public class CommandParser {
    Command baseCommand;
    public CommandParser(Command baseCommand){
        this.baseCommand = baseCommand;
    }
    public void run(String cmd){
        String[] args = cmd.split(" ");
        int index = 0;
        Command currentCmd = baseCommand;
        boolean success = false;
        while (index < args.length){
            for (Class<? extends Command> cmdClass : currentCmd.nextCmdClass) {
                Class<? extends Command> prevCommandClass = currentCmd.getClass();
                try {
                    cmdClass.cast(currentCmd).init(Arrays.copyOfRange(args,index,args.length));
                    success = true;
                    break;
                } catch (Exception e){
                    prevCommandClass.cast(currentCmd);
                }
            }
            if (!success){
                Game.getInstance().getLogger().error("Command parse error at arg "+index);
                return;
            }
            currentCmd.run();
            index = index + currentCmd.getConsumption() + 1;
        }
    }
}
