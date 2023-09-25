package me.carina.conveyor.common.stat;

import me.carina.conveyor.Game;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;

public class StatusEffect extends Feature {
    int duration;


    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {
        if (duration == -1) return;
        if (duration == 0){
            Game.getInstance().getContext().get(StatusEffects.class).remove(this);
        }
    }
}
