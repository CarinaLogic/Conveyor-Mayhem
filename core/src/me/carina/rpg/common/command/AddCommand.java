package me.carina.rpg.common.command;

public class AddCommand implements Command{
    @Override
    public String getPrefix() {
        return "add";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return true;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        if (args.length < 2) throw new IllegalArgumentException();
        int s = 0;
        for (int i = 1; i < args.length; i++) {
            s += parser.getInt(args[i]);
        }
        parser.setValue(args[0], s);
        return true;
    }
}
