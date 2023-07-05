package me.carina.rpg.common.command;

public class IfEqCommand implements Command{
    @Override
    public String getPrefix() {
        return "ifeq";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return true;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        Object a1 = parser.getValue(args[0]);
        Object a2 = parser.getValue(args[1]);
        int skipAmt = 1;
        if (args.length >= 3) skipAmt = (int) parser.getValue(args[2]);
        if (!a1.equals(a2)) parser.cursor += skipAmt;
        return true;
    }
}
