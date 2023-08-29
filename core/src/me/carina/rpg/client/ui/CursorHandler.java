package me.carina.rpg.client.ui;

public interface CursorHandler {
    default boolean goLeft(){return false;}
    default boolean goRight(){return false;}
    default boolean goUp(){return false;}
    default boolean goDown(){return false;}
    default boolean enter(){return false;}
    default boolean exit(){return false;}
    default int priority(){return 0;}
}
