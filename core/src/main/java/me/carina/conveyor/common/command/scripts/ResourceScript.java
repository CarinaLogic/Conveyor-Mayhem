package me.carina.conveyor.common.command.scripts;

import me.carina.conveyor.Game;
import me.carina.conveyor.common.block.Block;
import me.carina.conveyor.common.block.Blocks;
import me.carina.conveyor.common.block.ResourceFlow;
import me.carina.conveyor.common.block.ResourceMatcher;
import me.carina.conveyor.common.command.CommandExecutionPolicy;
import me.carina.conveyor.common.command.Script;
import me.carina.conveyor.common.util.Array;

public class ResourceScript extends Script {
    public ResourceScript(Block block, Blocks blocks, String... commands){
        super(commands);
        Game.getInstance().getCommandParser().setData("$resource",new ResourceMatcher());
        Game.getInstance().getCommandParser().setData("$block", block);
        Game.getInstance().getCommandParser().setData("$blocks", blocks);
        Game.getInstance().getCommandParser().setData("$inventory",new Array<ResourceFlow>());
        Game.getInstance().getCommandParser().setData("$buffer",new Array<ResourceMatcher>());
        setExecutionPolicy(CommandExecutionPolicy.blockProcessingPolicy());
    }
}
