package me.carina.rpg.common.command;

public abstract class Command {
    CommandParser parser;

    public void setParser(CommandParser parser) {
        this.parser = parser;
    }

    public CommandParser getParser() {
        return parser;
    }

    public Script getScript(){
        return getParser().getScript();
    }
}
