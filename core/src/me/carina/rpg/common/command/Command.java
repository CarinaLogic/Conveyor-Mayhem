package me.carina.rpg.common.command;

/**
 * Command system inspired by MIPS assembly
 */
public interface Command {
    String getPrefix();
    void run(CommandParser parser,String... args);
}
