package me.carina.rpg.common.command;

public class StringProvider implements CommandArgProvider<String>{
    @Override
    public String parse(String s) {
        return s;
    }

}
