package me.carina.conveyor.common.block;

import com.badlogic.gdx.math.Vector3;
import me.carina.conveyor.Game;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.command.CommandExecutionPolicy;
import me.carina.conveyor.common.command.Script;
import me.carina.conveyor.common.file.AssetGroup;

public class Block extends Feature {
    Direction direction;
    Vector3 size;
    Vector3 pos;
    String script;
    boolean updated;

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {
        Script script = new Script(this.script);
        script.setExecutionPolicy(CommandExecutionPolicy.blockProcessingPolicy());
        Game.getInstance().getCommandParser().immediateRun(script);
    }
    public boolean update(){
        updated = false;
        Script script = new Script(this.script);
        script.setExecutionPolicy(CommandExecutionPolicy.blockProcessingPolicy());
        Game.getInstance().getCommandParser().immediateRun(script);
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public Vector3 toGlobalCoords(Vector3 localCoords){
        return direction.transform(localCoords,size).add(pos);
    }

    public boolean contains(Vector3 coords){
        Vector3 d = pos.cpy().add(size);
        return pos.x <= coords.x && coords.x <= d.x && pos.y <= coords.y && coords.y <= d.y && pos.z <= coords.z && coords.z <= d.z;
    }
}
