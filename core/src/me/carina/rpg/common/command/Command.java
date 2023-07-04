package me.carina.rpg.common.command;

/**
 * Command system inspired by MIPS assembly
 */
public interface Command {
    String getPrefix();
    boolean init(CommandParser parser, String... args);
    boolean run(CommandParser parser,String... args);
}
