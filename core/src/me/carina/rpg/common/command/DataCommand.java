package me.carina.rpg.common.command;

public class DataCommand extends Command{
    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction(altNames = "$_=")
    public Object store(CommandData data, Object o){
        data.setValue(o);
        return o;
    }
}
