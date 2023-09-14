package me.carina.conveyor.common.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.IntIntMap;
import me.carina.conveyor.common.util.Array;

public class Script {
    Array<String> commands = new Array<>();
    int cursor = 0;
    float waitTime = 0;
    CommandExecutionPolicy executionPolicy = CommandExecutionPolicy.clientPolicy();
    IntIntMap jumpMap = new IntIntMap();
    //Recorded at destination
    IntIntMap jumpCounter = new IntIntMap();
    public Script(String... commands){
        this.commands.addAll(commands);
    }
    //true if it should be removed
    public boolean tick(CommandParser parser){
        if (waitTime != 0){
            waitTime -= Gdx.graphics.getDeltaTime();
            if (waitTime <= 0) waitTime = 0;
        }
        while (true){
            if (waitTime > 0) return false;
            if (cursor >= commands.size){
                reset();
                return true;
            }
            if (jumpMap.containsKey(cursor)){
                int j = jumpMap.get(cursor,0);
                jumpMap.remove(cursor,0);
                cursor = j;
                jumpCounter.put(j,jumpCounter.get(j,0)+1);
            }
            parser.parseCommand(commands.get(cursor));
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
        return commands.get(index);
    }
    public int getCommandLength(){
        return commands.size;
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

    public Script copyAs(CommandExecutionPolicy policy){
        Script script = new Script();
        script.commands = commands.copy();
        script.cursor = cursor;
        script.executionPolicy = policy;
        script.waitTime = waitTime;
        script.jumpMap = new IntIntMap(jumpMap);
        script.jumpCounter = new IntIntMap(jumpCounter);
        return script;
    }
}
