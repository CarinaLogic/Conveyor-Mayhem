package me.carina.rpg.common.command;

/**
 * Command system inspired by minecraft commands and MIPS assembly, mixed with my weird ideas
 */
public interface Command {
    String getPrefix();
    boolean init(CommandParser parser, String... args);
    boolean run(CommandParser parser,String... args);
}
