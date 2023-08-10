package me.carina.rpg.common.newcommand;

public class DataCommand extends Command{
    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction
    public Object store(CommandData data, Object o){
        data.setValue(o);
        return o;
    }
}
