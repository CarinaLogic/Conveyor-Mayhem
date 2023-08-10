package me.carina.rpg.common.newcommand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;

public class Script {
    String[] commands;
    int cursor = 0;
    float waitTime = 0;
    Queue<String> commandQueue = new Queue<>();
    public Script(String... commands){
        this.commands = commands;
    }
    //true if it should be removed
    public boolean tick(CommandParser parser){
        if (waitTime != 0){
            waitTime -= Gdx.graphics.getDeltaTime();
            if (waitTime <= 0) waitTime = 0;
        }
        while (true){
            if (waitTime > 0) return false;
            if (!commandQueue.isEmpty()) parser.parseCommand(commandQueue.first());
            else {
                if (cursor >= commands.length) return true;
                parser.parseCommand(commands[cursor]);
                cursor++;
            }
        }
    }

    public void queueCommand(String command){
        commandQueue.addLast(command);
    }
}
