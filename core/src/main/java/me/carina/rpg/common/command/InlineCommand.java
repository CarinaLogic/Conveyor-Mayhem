package me.carina.rpg.common.command;

import com.badlogic.gdx.utils.reflect.ClassReflection;

public class InlineCommand {
    //Use this when using lazy evaluation (like body of if statement)
    CommandParser parser;
    final String command;

    public InlineCommand(String cmd, CommandParser parser) {
        this.command = cmd;
        this.parser = parser;
    }

    public void parse(){
        parser.parseCommand(command);
    }
    public <T> T parse(Class<T> cls){
        Object o = parser.parseCommand(command);
        if (ClassReflection.isInstance(cls,o)){
            //noinspection unchecked
            return (T) o;
        }
        throw new CommandException(CommandException.ExceptionType.type_mismatch);
    }

    public String getCommand() {
        return this.command;
    }
}
