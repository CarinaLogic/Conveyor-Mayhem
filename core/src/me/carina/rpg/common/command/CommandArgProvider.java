package me.carina.rpg.common.command;

public interface CommandArgProvider<T> {
    T parse(String s);
}
