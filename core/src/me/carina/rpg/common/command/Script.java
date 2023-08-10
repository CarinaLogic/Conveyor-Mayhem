package me.carina.rpg.common.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.IntIntMap;

public class Script {
    String[] commands;
    int cursor = 0;
    int indent = 0;
    float waitTime = 0;
    boolean jumped = false;
    IntIntMap jumpMap = new IntIntMap();
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
            if (cursor >= commands.length) return true;
            if (jumpMap.containsKey(cursor)){
                int j = jumpMap.get(cursor,0);
                jumpMap.remove(cursor,0);
                cursor = j;
                jumped = true;
            }
            parser.parseCommand(commands[cursor]);
            cursor++;
            jumped = false;
        }
    }
    public String getCommandAt(int index){
        return commands[index];
    }
    public int getCommandLength(){
        return commands.length;
    }
    public void queueJump(int source, int dest){
        jumpMap.put(source, dest);
    }

    public boolean isJumped() {
        return jumped;
    }

    public void jumpToLabel(CommandLabel label){
        int i = 0;
        while (true){
            if (getCommandLength() >= i) throw new CommandException(CommandException.ExceptionType.label_not_found);
            if (label.matches(getCommandAt(i))) break;
            i++;
        }
        cursor = i;
    }
    public int getLabelIndex(CommandLabel label){
        int i = 0;
        while (true){
            if (getCommandLength() >= i) throw new CommandException(CommandException.ExceptionType.label_not_found);
            if (label.matches(getCommandAt(i))) break;
            i++;
        }
        return i;
    }
}
