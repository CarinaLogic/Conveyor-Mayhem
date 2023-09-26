package me.carina.conveyor.common.command.commands;

import me.carina.conveyor.common.block.Block;
import me.carina.conveyor.common.block.ResourceStack;
import me.carina.conveyor.common.command.Command;
import me.carina.conveyor.common.command.DataRange;

public class ResourceCommand extends Command {
    public void resource_type(String type){
        ResourceStack stack = getParser().getData("$resource", ResourceStack.class);
    }
    public void resource_id(String id){

    }
    public void resource_heat(DataRange range){

    }
    public void resource_size(DataRange range){

    }
    public void resource_flow(DataRange range){

    }
}
