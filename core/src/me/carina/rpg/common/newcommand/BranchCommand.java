package me.carina.rpg.common.newcommand;

public class BranchCommand extends Command{

    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction
    public void if_$(boolean condition, InlineCommand cmd){
        if (condition) cmd.parseLazy();
    }
    @CommandFunction
    public void if_$(boolean condition){
        if (!condition) getParser().getScript().cursor += 1;
    }
    @CommandFunction
    public void if_$_$_else(boolean condition, InlineCommand onTrue, InlineCommand onFalse){
        if (condition) onTrue.parseLazy();
        else onFalse.parseLazy();
    }
    @CommandFunction
    public void while_$(InlineCommand condition, InlineCommand cmd){
        //if conditions are met, push cursor 1 back so that next evaluation would be queued cmd and then this statement
        if (condition.parse(Boolean.class)){
            cmd.parseLazy();
            getScript().cursor--;
        }
    }
    @CommandFunction
    public void for_$(InlineCommand init, InlineCommand term, InlineCommand increment, InlineCommand cmd){
        init.parse();
        //TODO
    }
}
