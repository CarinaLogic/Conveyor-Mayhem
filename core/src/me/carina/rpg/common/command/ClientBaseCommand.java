package me.carina.rpg.common.command;

public class ClientBaseCommand extends Command{

    @Override
    public void next(){
        //nextCmdClass.addAll();
    }


    @Override
    public int getConsumption() {
        return -1;
    }

    @Override
    public void init(String... args) {

    }

    @Override
    public void run() {

    }
}
