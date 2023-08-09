package me.carina.rpg.common.newcommand;

public class CommandData<T> {
    CommandParser parser;
    String name;
    T value;
    public CommandData(CommandParser parser, String name, T value){
        this.parser = parser;
        this.name = name;
        this.value = value;
    }
    public T getValue(){
        return value;
    }
    public String getName(){
        return name;
    }

    public void setValue(T value) {
        this.value = value;
        parser.setData(getName(),getValue());
    }
}
