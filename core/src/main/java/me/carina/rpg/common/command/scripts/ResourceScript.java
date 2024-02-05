package me.carina.rpg.common.command.scripts;

import me.carina.rpg.Game;
import me.carina.rpg.common.block.Block;
import me.carina.rpg.common.block.Blocks;
import me.carina.rpg.common.block.ResourceFlow;
import me.carina.rpg.common.block.ResourceMatcher;
import me.carina.rpg.common.command.CommandExecutionPolicy;
import me.carina.rpg.common.command.Script;
import me.carina.rpg.common.util.Array;

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
