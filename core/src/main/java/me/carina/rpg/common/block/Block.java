package me.carina.rpg.common.block;

import com.badlogic.gdx.math.Vector3;
import me.carina.rpg.Game;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.command.scripts.ResourceScript;
import me.carina.rpg.common.file.AssetGroup;

public class Block extends Feature {
    Direction direction;
    Vector3 size;
    Vector3 pos;
    String script;
    boolean updated = false;

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {
        updated = false;
        Game.getInstance().getCommandParser().immediateRun(new ResourceScript(this,
            Game.getInstance().getContext().get(Blocks.class),script));
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
