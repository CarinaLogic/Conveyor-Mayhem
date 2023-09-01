package me.carina.rpg.common.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.IntIntMap;

public class Script {
    String[] commands;
    int cursor = 0;
    float waitTime = 0;
    CommandExecutionPolicy executionPolicy = CommandExecutionPolicy.clientPolicy();
    IntIntMap jumpMap = new IntIntMap();
    //Recorded at destination
    IntIntMap jumpCounter = new IntIntMap();
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
            if (cursor >= commands.length){
                reset();
                return true;
            }
            if (jumpMap.containsKey(cursor)){
                int j = jumpMap.get(cursor,0);
                jumpMap.remove(cursor,0);
                cursor = j;
                jumpCounter.put(j,jumpCounter.get(j,0)+1);
            }
            parser.parseCommand(commands[cursor]);
            cursor++;
        }
    }
    public void reset(){
        this.cursor = 0;
        this.jumpCounter.clear();
        this.jumpMap.clear();
    }
    public int getJumpCount(){
        return jumpCounter.get(cursor,0);
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
    public void jumpToLabel(CommandLabel label){
        int i = 0;
        while (true){
            if (getCommandLength() <= i) throw new CommandException(CommandException.ExceptionType.label_not_found);
            if (label.matches(getCommandAt(i))) break;
            i++;
        }
        cursor = i;
    }
    public int getLabelIndex(CommandLabel label){
        int i = 0;
        while (true){
            if (getCommandLength() <= i) throw new CommandException(CommandException.ExceptionType.label_not_found);
            if (label.matches(getCommandAt(i))) break;
            i++;
        }
        return i;
    }

    public float getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(float waitTime) {
        this.waitTime = waitTime;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }
}
