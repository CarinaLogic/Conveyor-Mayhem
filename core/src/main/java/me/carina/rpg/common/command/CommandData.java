package me.carina.rpg.common.command;

import com.badlogic.gdx.utils.reflect.ClassReflection;

public class CommandData {
    CommandParser parser;
    String name;
    Object value;
    public CommandData(CommandParser parser, String name, Object value){
        this.parser = parser;
        this.name = name;
        this.value = value;
    }
    public Object getValue(){
        return value;
    }
    public <T> T getValue(Class<T> cls){
        if (ClassReflection.isInstance(cls,value)){
            //noinspection unchecked
            return (T) value;
        }
        else throw new CommandException(CommandException.ExceptionType.type_mismatch);
    }
    public String getName(){
        return name;
    }

    public void setValue(Object value) {
        this.value = value;
        parser.setData(getName(),getValue());
    }

    @Override
    public String toString() {
        if (value == null) return "null";
        return value.toString();
    }
}
