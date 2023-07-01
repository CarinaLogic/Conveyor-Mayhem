package me.carina.rpg.common.command;

public class ArgParser {
    public static int getInt(String arg){
        return Integer.getInteger(arg);
    }
    public static String getLiteral(String target, String arg){
        if (!target.equals(arg)) throw new IllegalArgumentException();
        return target;
    }
    public static String getString(String arg){
        return arg;
    }
}
