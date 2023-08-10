package me.carina.rpg.common.newcommand;

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
    public String getName(){
        return name;
    }

    public void setValue(Object value) {
        this.value = value;
        parser.setData(getName(),getValue());
    }
}
