package me.carina.rpg.common.newcommand;

import com.badlogic.gdx.Gdx;

public class Script {
    String[] commands;
    int cursor = 0;
    float waitTime = 0;
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
            if (cursor >= commands.length) return true;
            if (waitTime > 0) return false;
            parser.parseCommand(commands[cursor]);
            cursor++;
        }
    }
}
