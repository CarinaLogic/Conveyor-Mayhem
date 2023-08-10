package me.carina.rpg.common.newcommand;

import java.util.Objects;

public class BooleanCommand extends Command{
    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction(altNames = {"$_&&"})
    public boolean $_and(boolean a, InlineCommand b){
        if (!a) return false;
        return b.parse(Boolean.class);
    }
    @CommandFunction(altNames = {"$_&&"})
    public boolean $_and(boolean a, boolean b){
        return a && b;
    }
    @CommandFunction(altNames = {"$_||"})
    public boolean $_or(boolean a, InlineCommand b){
        if (a) return true;
        return b.parse(Boolean.class);
    }
    @CommandFunction(altNames = {"$_||"})
    public boolean $_or(boolean a, boolean b){
        return a || b;
    }
    @CommandFunction(altNames = {"!"})
    public boolean not(boolean a){
        return !a;
    }
    @CommandFunction(altNames = {"=="})
    public boolean eq(Object a, Object b){
        return Objects.equals(a,b);
    }
    @CommandFunction(altNames = {"!="})
    public boolean ne(Object a, Object b){
        return !Objects.equals(a,b);
    }
}
