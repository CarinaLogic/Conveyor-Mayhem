package me.carina.rpg.common.newcommand;

import me.carina.rpg.common.util.Map;

public abstract class Command {
    CommandParser parser;
    public abstract boolean enabled();

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
