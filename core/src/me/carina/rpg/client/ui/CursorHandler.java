package me.carina.rpg.client.ui;

public interface CursorHandler {
    default boolean left(){return false;}
    default boolean right(){return false;}
    default boolean up(){return false;}
    default boolean down(){return false;}
    default boolean enter(){return false;}
    default boolean exit(){return false;}
}
