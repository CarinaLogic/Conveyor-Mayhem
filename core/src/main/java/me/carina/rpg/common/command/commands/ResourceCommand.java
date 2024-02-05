package me.carina.rpg.common.command.commands;

import com.badlogic.gdx.math.Vector3;
import me.carina.rpg.common.block.*;
import me.carina.rpg.common.command.Command;
import me.carina.rpg.common.command.CommandFunction;
import me.carina.rpg.common.command.DataRange;
import me.carina.rpg.common.file.Identifier;
import me.carina.rpg.common.resource.ResourceType;
import me.carina.rpg.common.util.Array;

public class ResourceCommand extends Command {
    @CommandFunction
    public void resource_type(ResourceType type){
        ResourceMatcher matcher = getParser().getData("$resource", ResourceMatcher.class);
        matcher.setType(type);
    }
    @CommandFunction
    public void resource_id(String id){
        ResourceMatcher matcher = getParser().getData("$resource", ResourceMatcher.class);
        matcher.setId(new Identifier(id));
    }
    @CommandFunction
    public void resource_heat(DataRange range){
        ResourceMatcher matcher = getParser().getData("$resource", ResourceMatcher.class);
        matcher.setHeat(range);
    }
    @CommandFunction
    public void resource_size(DataRange range){
        ResourceMatcher matcher = getParser().getData("$resource", ResourceMatcher.class);
        matcher.setSize(range);
    }
    @CommandFunction
    public void resource_flow(DataRange range){
        ResourceMatcher matcher = getParser().getData("$resource", ResourceMatcher.class);
        matcher.setFlow(range);
    }
    @CommandFunction
    public Vector3 absolute(Double x, Double y, Double z){
        return new Vector3(x.floatValue(),y.floatValue(),z.floatValue());
    }
    @CommandFunction
    public Vector3 relative(Double x, Double y, Double z){
        return getParser().getData("$block", Block.class).toGlobalCoords(new Vector3(x.floatValue(),y.floatValue(),z.floatValue()));
    }
    @CommandFunction
    public boolean block_$_exists(Vector3 coords){
        return getParser().getData("$blocks", Blocks.class).getBlock(coords) != null;
    }
    public boolean block_$_matches(){
        //TODO
        return true;
    }
    @CommandFunction
    public void resource_take(Vector3 coords){
        Blocks blocks = getParser().getData("$blocks", Blocks.class);
        ResourceFacing facing = blocks.getFacingItem(coords);
        if (facing != null){
            if (!facing.isClogged()){
                ResourceMatcher matcher = getParser().getData("$resource", ResourceMatcher.class);
                if (matcher.matches(facing.getFlow())){
                    Array<ResourceFlow> inv = getParser().getDataAsArray("$inventory",ResourceFlow.class);
                    inv.add(facing.getFlow());
                    facing.setClogged(false);
                }
                else facing.setClogged(true);
            }
        }
        getParser().setData("$resource", new ResourceMatcher());
    }
    @CommandFunction
    public void resource_craft_input(){
        ResourceMatcher matcher = getParser().getData("$resource", ResourceMatcher.class);
        Array<ResourceMatcher> buffer = getParser().getDataAsArray("$buffer",ResourceMatcher.class);
        buffer.add(matcher);
    }
    public void resource_craft_output(){
        ResourceMatcher matcher = getParser().getData("$resource", ResourceMatcher.class);
        Array<ResourceMatcher> buffer = getParser().getDataAsArray("$buffer",ResourceMatcher.class);
        Array<ResourceFlow> inv = getParser().getDataAsArray("$inventory", ResourceFlow.class);
        for (ResourceMatcher b : buffer) {
            boolean matched = false;
            for (ResourceFlow flow : inv) {
                if (b.matches(flow)){
                    inv.remove(flow);
                    matched = true;
                }
            }
        }
    }
    @CommandFunction
    public void resource_give(Vector3 coords){
        ResourceMatcher matcher = getParser().getData("$resource", ResourceMatcher.class);
        Blocks blocks = getParser().getData("$blocks", Blocks.class);
        blocks.addFacingItem(matcher.constructFlow(),coords);
        getParser().setData("$resource", new ResourceMatcher());
    }
}
