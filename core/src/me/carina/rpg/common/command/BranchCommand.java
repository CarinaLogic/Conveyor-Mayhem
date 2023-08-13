package me.carina.rpg.common.command;

import me.carina.rpg.common.util.Array;

public class BranchCommand extends Command{

    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction
    public void if_$(boolean condition, CommandLabel label){
        if (!condition){
            getScript().jumpToLabel(label);
        }
    }
    @CommandFunction
    public void while_$(boolean condition, CommandLabel label){
        if (condition) getScript().queueJump(getScript().getLabelIndex(label), getScript().cursor);
        else getScript().jumpToLabel(label);
    }
    //Assuming all functions does not set delay, otherwise timing issue occurs
    @CommandFunction
    public void for_$(InlineCommand init, InlineCommand term, InlineCommand increment, CommandLabel label){
        if (getScript().getJumpCount() != 0){
            increment.parse();
        }
        else {
            init.parse();
        }
        if (!term.parse(Boolean.class)){
            getScript().jumpToLabel(label);
        }
        else {
            getScript().queueJump(getScript().getLabelIndex(label), getScript().cursor);
        }
    }
    @CommandFunction
    public void for_$_in(CommandData data, Array<?> array, CommandLabel label){
        data.setValue(array.get(getScript().getJumpCount()));
        if (getScript().getJumpCount() < array.size-1){
            getScript().queueJump(getScript().getLabelIndex(label),getScript().cursor);
        }
    }
    @CommandFunction(altNames = {"j"})
    public void jump(CommandLabel label){
        getScript().jumpToLabel(label);
    }
}
