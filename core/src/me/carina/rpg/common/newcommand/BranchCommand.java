package me.carina.rpg.common.newcommand;

public class BranchCommand extends Command{

    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction()
    public void label(String string){
        //NOOP
    }
}
